package com.lab2.models;

public class Product {
    String firm;
    String type;
    String product;

    public Product(String firm, String type, String product) {
        this.firm = firm;
        this.type = type;
        this.product = product;
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
