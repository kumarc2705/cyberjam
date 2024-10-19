package com.cyberjam.model;

public class Person {
    private String name;
    private String experience;
    private String role;
    private String description;
    // Default constructor
    public Person() {}

    // Parameterized constructor
    public Person(String name, String experience, String role, String description) {
        this.name = name;
        this.experience = experience;
        this.role = role;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}