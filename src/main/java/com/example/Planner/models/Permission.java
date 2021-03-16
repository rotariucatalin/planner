package com.example.Planner.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "permision_id" )
    private int id;
    @Column(name = "permision_name")
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private List<User> users;

    public Permission() {
    }

    public Permission(String name, List<User> users) {
        this.name = name;
        this.users = users;
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
