package models;

import java.util.List;

public class Cart {
    private int userId;
    private String date;
    private List<ProductInCart> products;

    // Constructor
    public Cart() {}

    public Cart(int userId, String date, List<ProductInCart> products) {
        this.userId = userId;
        this.date = date;
        this.products = products;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCart> products) {
        this.products = products;
    }
}
