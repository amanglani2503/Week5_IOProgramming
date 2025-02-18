package com.ioprogramming.day2.practiceproblems.filterrecords;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private String name;
    private String email;
    private int age;
    private String city;

    // Default constructor required by Jackson
    public Person() {}

    // Parameterized constructor
    public Person(@JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("age") int age,
                  @JsonProperty("city") String city) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.city = city;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getCity() { return city; }
}
