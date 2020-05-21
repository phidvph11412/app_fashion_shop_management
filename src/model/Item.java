package model;

public class Item {
    private String itemID;
    private String itemName;
    private String itemImage;
    private float itemPrice;
    private int itemAmount;
    private String itemCategory;
    private String itemDescribe;

    public Item(String id, String name, String image, float price, int amount, String category, String describe) {
        this.itemID = id;
        this.itemName = name;
        this.itemImage = image;
        this.itemPrice = price;
        this.itemAmount = amount;
        this.itemCategory = category;
        this.itemDescribe = describe;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemDescribe() {
        return itemDescribe;
    }

    public void setItemDescribe(String itemDescribe) {
        this.itemDescribe = itemDescribe;
    }

}
