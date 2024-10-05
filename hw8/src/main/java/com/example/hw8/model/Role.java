package com.example.hw8.model;

import jakarta.persistence.*;

public enum Role {

    ADMIN("admin"), USER("user");

    private final String name;

    Role(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
