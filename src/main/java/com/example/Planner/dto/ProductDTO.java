package com.example.Planner.dto;

import com.example.Planner.models.Inquiry;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private int id;
    private String name;
    private String type;
    private String drawing;
    private int pcs;
    private String description;
    private Inquiry inquiry;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, String type, String drawing, int pcs, String description, Inquiry inquiry) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.drawing = drawing;
        this.pcs = pcs;
        this.description = description;
        this.inquiry = inquiry;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public int getPcs() {
        return pcs;
    }

    public void setPcs(int pcs) {
        this.pcs = pcs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }
}
