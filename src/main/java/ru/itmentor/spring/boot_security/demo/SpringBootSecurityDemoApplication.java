package ru.itmentor.spring.boot_security.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;

import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.dao.RoleDao;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.model.Role;

@SpringBootApplication
@AllArgsConstructor
public class SpringBootSecurityDemoApplication implements CommandLineRunner {

	private final RoleDao roleDao;
	private final UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Role adminrole = new Role(1, "ROLE_ADMIN");
		Role userrole = new Role(2, "ROLE_USER");
		roleDao.save(adminrole);
		roleDao.save(userrole);

		List<Role> admin_roles = new ArrayList<>();
		admin_roles.add(adminrole);
		admin_roles.add(userrole);

		userDao.save(new User(1, "admin", "admin", "admin@gmail.com", 35, admin_roles));

		List<Role> user_roles = new ArrayList<>();
		user_roles.add(userrole);

		userDao.save(new User(2, "user", "user", "user@gmail.com", 18, user_roles));

	}
}
