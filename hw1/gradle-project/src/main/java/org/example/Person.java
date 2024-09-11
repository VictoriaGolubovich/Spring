package org.example;

import  java.util.Random;

public class Person {
    private static final String[] names = new String[]{"Анатолий", "Глеб", "Виктор", "Николай", "Павел", "Константин", "Александр"};
    private static final Random random = new Random();
    private String name;
    private int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static Person create(){
        return new Person(names[random.nextInt(names.length)], random.nextInt(20, 45));
    }

}
