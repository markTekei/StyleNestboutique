package com.example.stylenestboutique.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String category;
    private double price;
    private String description;
    private int imageResource;
    private String imageUrl;
    private boolean isOnSale;
    private boolean isOutOfStock; // New field
    private String size;

    public Product(String name, String category, double price, String description, int imageResource) {
        this(name, category, price, description, imageResource, false, "M");
    }

    public Product(String name, String category, double price, String description, int imageResource, boolean isOnSale) {
        this(name, category, price, description, imageResource, isOnSale, "M");
    }
    
    public Product(String name, String category, double price, String description, int imageResource, boolean isOnSale, String size) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageResource = imageResource;
        this.isOnSale = isOnSale;
        this.size = size;
        this.isOutOfStock = false;
    }

    public Product(String name, String category, double price, String description, String imageUrl) {
        this(name, category, price, description, imageUrl, false, "M");
    }

    public Product(String name, String category, double price, String description, String imageUrl, boolean isOnSale) {
        this(name, category, price, description, imageUrl, isOnSale, "M");
    }
    
    public Product(String name, String category, double price, String description, String imageUrl, boolean isOnSale, String size) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isOnSale = isOnSale;
        this.size = size;
        this.isOutOfStock = false;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getImageResource() { return imageResource; }
    public String getImageUrl() { return imageUrl; }
    public boolean isOnSale() { return isOnSale; }
    public void setOnSale(boolean onSale) { isOnSale = onSale; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public boolean isOutOfStock() { return isOutOfStock; }
    public void setOutOfStock(boolean outOfStock) { isOutOfStock = outOfStock; }
}