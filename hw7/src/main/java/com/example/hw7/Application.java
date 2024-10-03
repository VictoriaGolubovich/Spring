package com.example.hw7;

import com.example.hw7.model.Person;
import com.example.hw7.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.hw7.model.Role;
import com.example.hw7.model.User;
import com.example.hw7.model.UserRole;
import com.example.hw7.repository.UserRepository;
import com.example.hw7.repository.UserRoleRepository;

import java.util.Random;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		UserRepository userRepository = ctx.getBean(UserRepository.class);
		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("$2a$12$LbAPCsHn8ZN5MUDqDmIX7e9n1YlDkCxEt0lW3Q2WuW0M1vteo8jvG"); // admin

		User user = new User();
		user.setLogin("user");
		user.setPassword("$2a$12$.dlnBAYq6sOUumn3jtG.AepxdSwGxJ8xA2iAPoCHSH61Vjl.JbIfq"); // user

		User anonymous = new User();
		anonymous.setLogin("anon");
		anonymous.setPassword("$2a$12$tPkyYzWCYUEePUFXUh3scesGuPum1fvFYwm/9UpmWNa52xEeUToRu"); // anon

		admin = userRepository.save(admin);
		user = userRepository.save(user);
		anonymous = userRepository.save(anonymous);

		UserRoleRepository userRoleRepository = ctx.getBean(UserRoleRepository.class);
		// id user_id role_name
		//  1       1     admin
		//  2       1     user
		//  3       2     user
		UserRole adminAdminRole = new UserRole();
		adminAdminRole.setUserId(admin.getId());
		adminAdminRole.setRoleName(Role.ADMIN.getName());
		userRoleRepository.save(adminAdminRole);

		UserRole adminUserRole = new UserRole();
		adminUserRole.setUserId(admin.getId());
		adminUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(adminUserRole);

		UserRole userUserRole = new UserRole();
		userUserRole.setUserId(user.getId());
		userUserRole.setRoleName(Role.USER.getName());
		userRoleRepository.save(userUserRole);

		PersonRepository personRepository = ctx.getBean(PersonRepository.class);

		String[] names = new String[]{"Nick", "Kate", "Viktor", "Paul", "Alex", "Nataly", "Jack"};
		Random random = new Random();

		for (int i = 1; i <= 5; i++) {
			Person person = new Person();
			String name = names[random.nextInt(names.length)];
			person.setName(name);
			person.setEmail(name+ "@mail.ru");
			personRepository.save(person);

		}



	}

}

