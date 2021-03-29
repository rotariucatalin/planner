package com.example.Planner.dto;

import com.example.Planner.models.Company;
import com.example.Planner.models.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ActivityDTO implements Serializable {

    private int id;
    private String subject;
    private String reference;
    private String type;
    private Company company;
    private ContactDTO contactDTO;
    @JsonIgnore
    private Contact contact;
    private String date;
    private String status;
    private String location;

    public ActivityDTO() {
    }

    public ActivityDTO(int id, String subject, String reference, String type, Company company, ContactDTO contactDTO, Contact contact, String date, String status, String location) {
        this.id = id;
        this.subject = subject;
        this.reference = reference;
        this.type = type;
        this.company = company;
        this.contactDTO = contactDTO;
        this.contact = contact;
        this.date = date;
        this.status = status;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ContactDTO getContactDTO() {
        return contactDTO;
    }

    public void setContactDTO(ContactDTO contactDTO) {
        this.contactDTO = contactDTO;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
