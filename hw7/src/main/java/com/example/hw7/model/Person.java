package com.example.hw7.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    private String name;
    private String email;

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId(){
//        return id;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail(){
//        return email;
//    }

//    public void setEmail(String email) {
//        this.email = email;
//    }
}
