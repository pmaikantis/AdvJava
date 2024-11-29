package com.fragrancestore.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private int fragranceId; 
    private int categoryId;  
    private String category; 
    private String title;
    private String perfumer;
    private String description;
    private String notes;
    private double price;
    private Date releaseDate;
    private String image; 
    private String thumbnail; 

    // Constructor
    public Product(int fragranceId, int categoryId, String category, String title, String perfumer, String description, String notes, double price, Date releaseDate, String image, String thumbnail) {
        this.fragranceId = fragranceId;
        this.categoryId = categoryId;
        this.setCategory(category); 
        this.title = title;
        this.perfumer = perfumer;
        this.description = description;
        this.notes = notes;
        this.price = price;
        this.releaseDate = releaseDate;
        this.image = image;
        this.thumbnail = thumbnail; 
    }

    public Product() {
    }

    public int getFragranceId() {
        return fragranceId;
    }

    public void setFragranceId(int fragranceId) {
        this.fragranceId = fragranceId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {

        if (category != null && !category.isEmpty()) {
            this.category = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();
        } else {
            this.category = category; // Handle null or empty cases
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(String perfumer) {
        this.perfumer = perfumer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getFormattedReleaseDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return releaseDate != null ? formatter.format(releaseDate) : "N/A";
    }

    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "fragranceId=" + fragranceId +
                ", categoryId=" + categoryId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", perfumer='" + perfumer + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", image='" + image + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
