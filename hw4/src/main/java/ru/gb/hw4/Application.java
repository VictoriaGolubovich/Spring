package ru.gb.hw4;
/**
 * Разработайте веб-приложение с использованием Spring MVC и Thymeleaf, которое
 * отображает список пользователей из базы данных.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
