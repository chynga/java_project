package com.egadgets.demo3.model;

public class Image {

    private int id;
    private String imageUrl;
    private int productId;

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image(int id, String imageUrl, int productId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", productId=" + productId +
                '}';
    }
}
