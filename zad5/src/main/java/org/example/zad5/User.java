package org.example.zad5;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String lastName;

    public User(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
}
