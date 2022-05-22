package com.egadgets.demo3.model;

import java.util.Date;

public class Message {
    private int id;
    private String sentence;
    private Writer writer;
    private Date date;
    private int customerId;
    private int productId;

    public Message(String sentence, Writer writer, int customerId, int productId) {
        this.sentence = sentence;
        this.writer = writer;
        this.date = new Date();
        this.customerId = customerId;
        this.productId = productId;
    }

    public Message(int id, String sentence, String writer, Date date, int customerId, int productId) {
        this.id = id;
        this.sentence = sentence;
        if (writer.equals("c")) {
            this.writer = Writer.CUSTOMER;
        } else if (writer.equals("s")) {
            this.writer = Writer.SELLER;
        } else {
            return;
        }
        this.date = date;
        this.productId = productId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
