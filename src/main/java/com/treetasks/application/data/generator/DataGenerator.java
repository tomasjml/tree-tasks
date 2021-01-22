package com.treetasks.application.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;

import com.treetasks.application.data.service.CategoryRepository;
import com.treetasks.application.data.entity.Category;
import com.treetasks.application.data.service.UsersRepository;
import com.treetasks.application.data.entity.User;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(CategoryRepository categoryRepository, UsersRepository usersRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (categoryRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Category entities...");
            ExampleDataGenerator<Category> categoryRepositoryGenerator = new ExampleDataGenerator<>(Category.class,
                    LocalDateTime.of(2021, 1, 20, 0, 0, 0));
            categoryRepositoryGenerator.setData(Category::setId, DataType.ID);
            categoryRepositoryGenerator.setData(Category::setName, DataType.WORD);
            categoryRepositoryGenerator.setData(Category::setDescription, DataType.WORD);
            categoryRepositoryGenerator.setData(Category::setDateOfCreation, DataType.DATE_OF_BIRTH);
            categoryRepositoryGenerator.setData(Category::setActive, DataType.BOOLEAN_90_10);
            categoryRepository.saveAll(categoryRepositoryGenerator.create(100, seed));

            logger.info("... generating 100 Users entities...");
            ExampleDataGenerator<User> usersRepositoryGenerator = new ExampleDataGenerator<>(User.class,
                    LocalDateTime.of(2021, 1, 20, 0, 0, 0));
            usersRepositoryGenerator.setData(User::setUsername, DataType.FIRST_NAME);
            usersRepositoryGenerator.setData(User::setId, DataType.ID);
            usersRepositoryGenerator.setData(User::setFirstName, DataType.FIRST_NAME);
            usersRepositoryGenerator.setData(User::setLastName, DataType.LAST_NAME);
            usersRepositoryGenerator.setData(User::setEmail, DataType.EMAIL);
            usersRepositoryGenerator.setData(User::setPhone, DataType.PHONE_NUMBER);
            usersRepositoryGenerator.setData(User::setDateOfBirth, DataType.DATE_OF_BIRTH);
            usersRepositoryGenerator.setData(User::setDateOfCreation, DataType.DATE_OF_BIRTH);
            usersRepositoryGenerator.setData(User::setPassword, DataType.WORD);
            usersRepositoryGenerator.setData(User::setActive, DataType.BOOLEAN_90_10);
            usersRepository.saveAll(usersRepositoryGenerator.create(100, seed));

            logger.info("Generated demo data");
        };
    }

}