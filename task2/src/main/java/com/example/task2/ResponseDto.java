package com.example.task2;

import java.util.List;

public class ResponseDto {

    private String name;
    private List<SubclassDto> subClasses;

    public ResponseDto() {
    }

    public ResponseDto(String name, List<SubclassDto> subClasses) {
        this.name = name;
        this.subClasses = subClasses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubclassDto> getSubClasses() {
        return subClasses;
    }

    public void setSubClasses(List<SubclassDto> subClasses) {
        this.subClasses = subClasses;
    }
}
