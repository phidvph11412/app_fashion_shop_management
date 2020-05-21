package model;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private String customerName;
    private ArrayList<Item> listItem = new ArrayList<>();

    public Order(int orderID, String customerName, ArrayList<Item> listItem) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.listItem = listItem;
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
