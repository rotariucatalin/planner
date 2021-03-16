package com.example.Planner.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_username")
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_active")
    private String active;
    @Column(name = "user_type")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name = "user_permissions",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "permision_id", referencedColumnName = "permision_id")
              )
    private Set<Permission> permissions;

    public User(String username, String password, String email, String active, String role, Set<Permission> permissions) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.role = role;
        this.permissions = permissions;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
