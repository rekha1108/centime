package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameCharacterService {

    @Autowired
    GameCharacterRepository gameCharacterRepository;

    @LogMethodParam
    public GameCharacter getGameCharacterById(Integer id) {
        return gameCharacterRepository.findById(id).orElseThrow();
    }

    public List<ResponseDto> getGameCharacters() {

        List<GameCharacter> gameCharacters = gameCharacterRepository.findAll();
        List<ResponseDto> result = convertToNestedResponse(gameCharacters);
        return result;
    }


    private List<ResponseDto> convertToNestedResponse(List<GameCharacter> gameCharacters) {
        Map<Integer, GameCharacter> idMap = new HashMap<>();
        for (GameCharacter gameCharacter : gameCharacters) {
            idMap.put(gameCharacter.getId(), gameCharacter);
        }

        Map<Integer, List<SubclassDto>> childMap = new HashMap<>();
        for (GameCharacter dto : gameCharacters) {
            if (dto.getParentId() != 0) {
                childMap.computeIfAbsent(dto.getParentId(), k -> new ArrayList<>())
                        .add(new SubclassDto(dto.getName()));
            }
        }

        List<ResponseDto> nestedResponse = new ArrayList<>();
        for (GameCharacter dto : gameCharacters) {
            if (dto.getParentId() == 0) {
                List<SubclassDto> subclasses = childMap.getOrDefault(dto.getId(), new ArrayList<>());
                nestedResponse.add(new ResponseDto(dto.getName(), subclasses));
            }
        }

        return nestedResponse;
    }


}
