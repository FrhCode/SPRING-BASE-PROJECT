package com.farhan.baseproject.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.farhan.baseproject.model.Category;
import com.farhan.baseproject.model.User;
import com.farhan.baseproject.repository.CategoryRepository;
import com.farhan.baseproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile({ "dev", "staging", "default" })
public class DevRunner implements CommandLineRunner {
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		generateUser();

	}

	private void generateUser() {
		User user = new User();
		user.setName("Mohammad Farhan Fajrul Haq");
		user.setEmail("farhan7534031b@gmail.com");
		user.setPhoneNumber("082188513499");
		user.setPassword(passwordEncoder.encode("indonesia123B"));
		user.setProfileImage("");
		userRepository.save(user);
	}

}
