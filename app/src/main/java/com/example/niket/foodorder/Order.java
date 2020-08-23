package com.example.niket.foodorder;

/**
 * Created by niket on 08-10-2017.
 */

public class Order {
    String name;
    int price;
    int quantity;
    int total;
    String thumbnail;

    public Order() {
    }

    public int getprice() {
        return price;
    }

    public void setprice(int id) {
        this.price = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getquantity() {
        return quantity;
    }

    public void getquantity(int quantity) {
        this.quantity = quantity;
    }

    public double gettotal() {
        return price;
    }

    public void settotal(int total) {
        this.total = total;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
