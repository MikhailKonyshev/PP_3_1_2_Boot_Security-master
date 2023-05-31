package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserDAOServiceImp;

import java.util.*;

@SpringBootApplication
public class SpringBootSecurityDemoApplication implements CommandLineRunner {

	private final UserDAOServiceImp userDAOServiceImp;

	public SpringBootSecurityDemoApplication(UserDAOServiceImp userDAOServiceImp) {
		this.userDAOServiceImp = userDAOServiceImp;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<User> allUsers = userDAOServiceImp.getAllUsers();

		if (allUsers.isEmpty()) {
			Role admin = new Role("ROLE_ADMIN");
			Role user = new Role("ROLE_USER");
			HashSet<Role> roles = new HashSet<>();
			roles.add(admin);
			roles.add(user);
			userDAOServiceImp.saveUser(new User("admin", "admin", 18, "admin@admin.com", "admin", roles));
		}
	}
}
