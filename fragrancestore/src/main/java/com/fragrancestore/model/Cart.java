package com.fragrancestore.model;

public class Cart extends Product {
    private int quantity;

    public Cart() {
        
    }

    
    public int getQuantity() {
        return quantity;
    }

    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    public int getProductId() {
        return super.getFragranceId(); 
    }

    public void setProductId(int productId) {
        super.setFragranceId(productId); 
    }

   
    @Override
    public String getTitle() {
        return super.getTitle();
    }

   
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

  
    @Override
    public String getImage() {
        return super.getImage();
    }

    
    @Override
    public void setImage(String image) {
        super.setImage(image);
    }

    
    @Override
    public double getPrice() {
        return super.getPrice();
    }

   
    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }
}
