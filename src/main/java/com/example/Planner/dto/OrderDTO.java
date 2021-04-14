package com.example.Planner.dto;

import javax.persistence.Column;
import java.io.Serializable;

public class OrderDTO implements Serializable {

    private int id;
    private String orderNr;
    private String orderDate;
    private String orderDescription;

    public OrderDTO() {
    }

    public OrderDTO(int id, String orderNr, String orderDate, String orderDescription) {
        this.id = id;
        this.orderNr = orderNr;
        this.orderDate = orderDate;
        this.orderDescription = orderDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public void setOrderNr(String orderNr) {
        this.orderNr = orderNr;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
}
