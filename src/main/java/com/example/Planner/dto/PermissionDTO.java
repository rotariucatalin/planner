package com.example.Planner.dto;

public class PermissionDTO {

    private int id;
    private String name;

    public PermissionDTO() {
    }

    public PermissionDTO(int id, String name) {
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
