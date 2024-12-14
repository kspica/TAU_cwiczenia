package org.example;

public class Order {
    String itemNumber;
    Integer quantity;
    Integer price;

    public Order(String itemNumber, Integer quantity, Integer price) {
        this.itemNumber = itemNumber;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
