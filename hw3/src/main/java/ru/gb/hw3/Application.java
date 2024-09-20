package ru.gb.hw3;

/**
 * Используя Spring, создайте серверное приложение, которое обрабатывает HTTP-запросы
 * и возвращает JSON-ответы.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
