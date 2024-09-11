package org.example;

public class Program {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Person person = Person.create();
            System.out.println(person);
        }
    }
}
