package com.letsgo.blog;

import com.letsgo.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@SpringBootTest
class BlogBackendApisApplicationTests {

	private final static Logger log = Logger.getLogger(BlogBackendApisApplicationTests.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void repositoryTest() {
		String className = this.userRepository.getClass().getName();
		System.out.println("Hello Vishal in Test after package");
		System.out.println("Implementation Classname for UserRepository "+ className);
		log.info("Using logger Implementation Classname for UserRepository "+ className);
	}

}
