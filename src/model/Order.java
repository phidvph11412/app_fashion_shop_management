package model;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private String customerName;
    private ArrayList<Item> listItem = new ArrayList<>();
    private String status;
    private int amount;
    private String itemId;
    private float price;

    public Order(int orderID, String customerName, ArrayList<Item> listItem) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.listItem = listItem;
    }

    public Order(String name, String id, int amount, float price, String status) {
        this.customerName = name;
        this.itemId = id;
        this.amount = amount;
        this.price = price;
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Order(String customerName, int amount, String status) {
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

    public Order(String customerName, String itemId, int amount, String status) {
        this.customerName = customerName;
        this.itemId = itemId;
        this.amount = amount;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }
}
