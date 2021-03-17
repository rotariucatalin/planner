package com.example.Planner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;
    @Column(name = "company_name")
    private String name;
    @Column(name = "company_type")
    private String type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "contacts_companies",
                joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id"),
                inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
              )
    private Set<Contact> contactSet;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Activity> activityList;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Contact> getContactSet() {
        return contactSet;
    }

    public void setContactSet(Set<Contact> contactSet) {
        this.contactSet = contactSet;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }
}
