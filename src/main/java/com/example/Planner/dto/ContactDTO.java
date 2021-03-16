package com.example.Planner.dto;

public class ContactDTO {

    private int id;
    private String name;

    public ContactDTO() {
    }

    public ContactDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
