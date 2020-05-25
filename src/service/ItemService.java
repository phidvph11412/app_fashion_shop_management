package service;

import model.Item;

import java.sql.ResultSet;

public class ItemService  implements IItemService {
    DAL dal;

    public ItemService() {
        dal = new DAL();
    }

    public ResultSet getListItem() {
        return dal.getData("select * from item;");
    }
    public ResultSet getListItemID() {
        return dal.getData("select itemId from item;");
    }
    public ResultSet getItemByID(String id){
        return dal.getData("select * from item where itemId = '"+ id +"';");
    }

    public boolean saveDataItem(Item item) {
        return dal.updateData("insert into item() " +
                "values('" + item.getItemID() + "', '" + item.getItemName() + "', '" + item.getItemImage() + "', " + item.getItemPrice() + " , " + item.getItemAmount() + ", '" + item.getItemCategory() + "', '" + item.getItemDescribe() + "');");
    }

    public boolean deleteItemByID(String id) {
        return dal.updateData("delete from item " +
                "where itemId = '"+ id +"';" );
    }

    public boolean editItemByID(String id , Item item) {
        return dal.updateData( "update item " +
                "set itemId = '"+ item.getItemID() +"', item_name = '"+ item.getItemName() +"' , image = '"+ item.getItemImage() +"' , price = "+ item.getItemPrice() +", amount = "+ item.getItemAmount() +", catelory = '"+ item.getItemCategory() +"', describes = '"+ item.getItemDescribe() +"' " +
                " where itemId = '"+ id +"';");
    }

    @Override
    public ResultSet getListItemByCategoryAndPrice(String category, float minPrice, float maxPrice) {
        return dal.getData("select * from item where catelory = '"+ category +"' and price >= "+ minPrice +" and price < "+ maxPrice +";");
    }

    public ResultSet getListItemByCategory(String category) {
        return dal.getData("select * from item where catelory = '"+ category +"' ;");
    }
    public ResultSet getListItemByPrice(float minPrice , float maxPrice) {
        return dal.getData("select * from item where price >= "+ minPrice +" and price < "+ maxPrice +" ;");
    }
}


