package com.treetasks.application.data.service;

import com.treetasks.application.data.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;
import java.time.LocalDate;

@Service
public class CategoryService extends CrudService<Category, Integer> {

    private CategoryRepository repository;

    public CategoryService(@Autowired CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected CategoryRepository getRepository() {
        return repository;
    }

}
