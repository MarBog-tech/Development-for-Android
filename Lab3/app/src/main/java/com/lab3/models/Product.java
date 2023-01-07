package com.lab3.models;

public class Product {
    int id;
    String firm;
    String type;
    String product;

    public Product(int id, String firm, String type, String product) {
        this.id = id;
        this.firm = firm;
        this.type = type;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
