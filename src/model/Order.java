package model;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private String customerName;
    private ArrayList<Item> listItem = new ArrayList<>();
    private boolean status;
    private int amount;

    public Order(int orderID, String customerName, ArrayList<Item> listItem) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.listItem = listItem;
    }

    public Order(int orderID, String customerName, ArrayList<Item> listItem, boolean status, int amount) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.listItem = listItem;
        this.status = status;
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
