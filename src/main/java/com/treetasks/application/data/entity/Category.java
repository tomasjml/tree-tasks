package com.treetasks.application.data.entity;

import javax.persistence.Entity;

import com.treetasks.application.data.AbstractEntity;
import java.time.LocalDate;

@Entity(name="category")
public class Category extends AbstractEntity {
    private Integer id;
    private String name;
    private String description;
    private LocalDate dateOfCreation;
    private boolean active;

    @java.lang.Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }
    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
