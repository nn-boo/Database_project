package ru.nnboo.NNBOO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nnboo.NNBOO.entity.Role;
import ru.nnboo.NNBOO.repository.RoleJPA;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NNBOOApplication {

	@Autowired
	RoleJPA roleRepository;

	@PostConstruct
	public void roles(){
		if(roleRepository.findRoleByName("ROLE_USER") == null){
			Role role_user = new Role("ROLE_USER");
			roleRepository.save(role_user);
		}
		if(roleRepository.findRoleByName("ROLE_ADMIN") == null){
			Role role_admin = new Role("ROLE_ADMIN");
			roleRepository.save(role_admin);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(NNBOOApplication.class, args);
	}

}
