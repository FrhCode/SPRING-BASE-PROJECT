package com.farhan.baseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farhan.baseproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
