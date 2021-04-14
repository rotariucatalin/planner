package com.example.Planner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "company_email")
    private String email;
    @Column(name = "company_phone")
    private String phoneNumber;
    @Column(name = "company_post_address")
    private String postAddress;
    @Column(name = "company_city")
    private String city;
    @Column(name = "company_website")
    private String website;
    @Column(name = "company_description")
    private String companyDescription;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "company_sales_person")
    private Contact salesPerson;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "company_sales_company")
    private Company salesCompany;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
//    @JoinTable( name = "contacts_companies",
//                joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id"),
//                inverseJoinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
//              )
    private List<Contact> contact;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public Company getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(Company salesCompany) {
        this.salesCompany = salesCompany;
    }

    public Contact getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(Contact salesPerson) {
        this.salesPerson = salesPerson;
    }
}
