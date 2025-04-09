package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-character")
public class GameCharacterController {

    @Autowired
    GameCharacterService gameCharacterService;

    @GetMapping("/health")
    public ResponseEntity<String> getHealth(){
        return ResponseEntity.ok("Up");
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameCharacter> getGameCharacterById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(gameCharacterService.getGameCharacterById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> getGameCharacters(){
        return ResponseEntity.ok(gameCharacterService.getGameCharacters());
    }



}
