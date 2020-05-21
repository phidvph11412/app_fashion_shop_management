package model;

public class Oder {
    private String customerName;
    private String itemID;
    private int amount;
    private float price;
    private boolean status = false;

    public Oder() {

    }

    public Oder(String customerName, String itemID, int amount, float price, boolean status) {
        this.customerName = customerName;
        this.itemID = itemID;
        this.amount = amount;
        this.price = price;
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
