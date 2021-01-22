package com.treetasks.application.data.service;

import com.treetasks.application.data.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;

@Service
public class UsersService extends CrudService<User, Integer> {

    private UsersRepository repository;

    public UsersService(@Autowired UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    protected UsersRepository getRepository() {
        return repository;
    }

}
