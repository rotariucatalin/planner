package com.example.Planner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int id;
    @Column(name = "activity_subject")
    private String subject;
    @Column(name = "activity_reference")
    private String reference;
    @Column(name = "activity_type")
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_company", nullable = false)
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_contact", nullable = false)
    @JsonIgnore
    private Contact contact;
    @Column(name = "activity_date")
    private String date;
    @Column(name = "activity_status")
    private String status;
    @Column(name = "activity_location")
    private String location;

    public Activity(String subject, String reference, String type, Company company, Contact contact, String date, String status, String location) {
        this.subject = subject;
        this.reference = reference;
        this.type = type;
        this.company = company;
        this.contact = contact;
        this.date = date;
        this.status = status;
        this.location = location;
    }

    public Activity() {

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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDate localDate = LocalDate.parse(date);

        return formatter.format(localDate);

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

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", reference='" + reference + '\'' +
                ", type='" + type + '\'' +
                ", company=" + company +
                ", contact='" + contact + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
