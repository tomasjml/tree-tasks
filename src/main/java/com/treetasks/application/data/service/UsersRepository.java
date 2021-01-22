package com.treetasks.application.data.service;

import com.treetasks.application.data.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface UsersRepository extends JpaRepository<User, Integer> {

}