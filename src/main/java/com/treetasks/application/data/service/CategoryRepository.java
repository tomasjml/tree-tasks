package com.treetasks.application.data.service;

import com.treetasks.application.data.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}