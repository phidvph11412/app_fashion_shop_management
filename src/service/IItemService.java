package service;

import model.Item;

import java.sql.ResultSet;

public interface IItemService {
    public ResultSet getListItem();
    public ResultSet getListItemID();
    public ResultSet getItemByID(String id);
    public boolean saveDataItem(Item item);
    public boolean deleteItemByID(String id);
    public boolean editItemByID(String id , Item item);
    ResultSet getListItemByCategoryAndPrice(String category, float minPrice, float maxPrice);
    ResultSet getListItemByCategory(String category);
    ResultSet getListItemByPrice(float minPrice , float maxPrice);
}
