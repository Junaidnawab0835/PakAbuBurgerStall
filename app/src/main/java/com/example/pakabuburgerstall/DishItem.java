package com.example.pakabuburgerstall;

public class DishItem {
    String name;
    int price;
    String id;
    int quantity;
    int thumbail;

    public DishItem(String name, int price, String id, int quantity, int thumbail) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
        this.thumbail = thumbail;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getThumbail() {
        return thumbail;
    }

    public void setThumbail(int thumbail) {
        this.thumbail = thumbail;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
