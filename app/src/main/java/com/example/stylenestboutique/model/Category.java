package com.example.stylenestboutique.model;

public class Category {
    private String name;
    private String imageUrl;
    private int imageResource;

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Category(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public int getImageResource() { return imageResource; }
}