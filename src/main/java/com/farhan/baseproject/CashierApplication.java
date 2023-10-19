package com.farhan.baseproject;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farhan.baseproject.model.Category;
import com.farhan.baseproject.model.User;
import com.farhan.baseproject.repository.CategoryRepository;
import com.farhan.baseproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RestController
@EnableJpaAuditing
@RequiredArgsConstructor
public class CashierApplication {
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	@GetMapping
	public List<User> index() {
		return userRepository.findAll();
	}

	@GetMapping("/tes")
	public Category index1() {
		Category category = new Category();
		category.setName("Makanan");
		category.setActive(true);
		categoryRepository.save(category);
		return category;
	}

	public static void main(String[] args) {
		SpringApplication.run(CashierApplication.class, args);
	}

}
