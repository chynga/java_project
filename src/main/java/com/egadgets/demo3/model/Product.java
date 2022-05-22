package com.egadgets.demo3.model;

import java.util.ArrayList;

public class Product {
    private int id;
    private String productName;
    private String description;
    private float price;
    private int sellerId;
    private ArrayList<Image> images = new ArrayList<>();

    public Product(String productName, String description, float price, int sellerId, ArrayList<Image> images) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.sellerId = sellerId;
        this.images = images;
    }

    public Product(int id, String productName, String description, float price, int sellerId, ArrayList<Image> images) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.sellerId = sellerId;
        this.images = images;
    }

    public Product() {

    }

    public Image getImage() {
        return this.images.get(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
