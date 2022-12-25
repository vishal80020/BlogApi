package com.letsgo.blog;

import com.letsgo.blog.config.AppConstants;
import com.letsgo.blog.entity.Role;
import com.letsgo.blog.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogBackendApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogBackendApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return  new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));

		Role roleAdmin = new Role();
		roleAdmin.setId(AppConstants.ADMIN_USER_ROLE_ID);
		roleAdmin.setName(AppConstants.ADMIN_USER);

		Role roleNormal = new Role();
		roleNormal.setId(AppConstants.NORMAL_USER_ROLE_ID);
		roleNormal.setName(AppConstants.NORMAL_USER);

		List<Role> roleList = new ArrayList<>();
		roleList.add(roleAdmin);
		roleList.add(roleNormal);
		List<Role> result = this.roleRepository.saveAll(roleList);

	}

}
