package com.example.Planner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inquiries")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "inq_nr")
    private int inqNr;
    @Column(name = "inq_revision")
    private int inqRevision;
    @Column(name = "inq_description")
    private String inqDescription;
    @Column(name = "inq_date")
    private String inqDate;
    @Column(name = "inq_rate")
    private Float inqRate;
    @Column(name = "inq_total_price")
    private Float inqPrice;
    @Column(name = "inq_currency")
    private String inqCurrency;
    @Column(name = "inq_status")
    private String inqStatus;
    @Column(name = "inq_priority")
    private String inqPriority;

    @OneToOne
    @JoinColumn(name = "inq_company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "inq_contact_person_id")
    private Contact contact;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "inq_sales_company_id")
    private Company salesCompany;

    @OneToOne
    @JoinColumn(name = "inq_sales_contact_id")
    private Contact salesContact;

    @OneToOne
    @JoinColumn(name = "inq_project_manager_id")
    private Contact inqProjectManager;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inquiry")
    private List<Product> productList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inquiry")
    private List<Order> orderList;

    public Inquiry() {
    }

    public Inquiry(int inqNr, int inqRevision, String inqDescription, String inqDate, Float inqRate, Float inqPrice, String inqCurrency, String inqStatus, String inqPriority, Company company, Contact contact, Company salesCompany, Contact salesContact, Contact inqProjectManager, List<Product> productList, List<Order> orderList) {
        this.inqNr = inqNr;
        this.inqRevision = inqRevision;
        this.inqDescription = inqDescription;
        this.inqDate = inqDate;
        this.inqRate = inqRate;
        this.inqPrice = inqPrice;
        this.inqCurrency = inqCurrency;
        this.inqStatus = inqStatus;
        this.inqPriority = inqPriority;
        this.company = company;
        this.contact = contact;
        this.salesCompany = salesCompany;
        this.salesContact = salesContact;
        this.inqProjectManager = inqProjectManager;
        this.productList = productList;
        this.orderList = orderList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInqNr() {
        return inqNr;
    }

    public void setInqNr(int inqNr) {
        this.inqNr = inqNr;
    }

    public int getInqRevision() {
        return inqRevision;
    }

    public void setInqRevision(int inqRevision) {
        this.inqRevision = inqRevision;
    }

    public String getInqDescription() {
        return inqDescription;
    }

    public void setInqDescription(String inqDescription) {
        this.inqDescription = inqDescription;
    }

    public String getInqDate() {
        return inqDate;
    }

    public void setInqDate(String inqDate) {
        this.inqDate = inqDate;
    }

    public Float getInqRate() {
        return inqRate;
    }

    public void setInqRate(Float inqRate) {
        this.inqRate = inqRate;
    }

    public Float getInqPrice() {
        return inqPrice;
    }

    public void setInqPrice(Float inqPrice) {
        this.inqPrice = inqPrice;
    }

    public String getInqCurrency() {
        return inqCurrency;
    }

    public void setInqCurrency(String inqCurrency) {
        this.inqCurrency = inqCurrency;
    }

    public String getInqStatus() {
        return inqStatus;
    }

    public void setInqStatus(String inqStatus) {
        this.inqStatus = inqStatus;
    }

    public String getInqPriority() {
        return inqPriority;
    }

    public void setInqPriority(String inqPriority) {
        this.inqPriority = inqPriority;
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

    public Company getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(Company salesCompany) {
        this.salesCompany = salesCompany;
    }

    public Contact getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(Contact salesContact) {
        this.salesContact = salesContact;
    }

    public Contact getInqProjectManager() {
        return inqProjectManager;
    }

    public void setInqProjectManager(Contact inqProjectManager) {
        this.inqProjectManager = inqProjectManager;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
