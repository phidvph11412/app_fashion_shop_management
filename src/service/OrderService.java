package service;

import model.Item;
import model.Order;

import java.util.ArrayList;

public class OrderService implements IOrderService {
    DAL dal;
    public OrderService(){
        dal = new DAL();
    }
    public boolean saveOrder(Order order){
        ArrayList<Item> listItem = order.getListItem();
        if (listItem.size() >= 1){
            for (Item item : listItem){
                dal.updateData("insert into orders(customerName, itemId, amount, price) " +
                        "values('"+ order.getCustomerName() +"','"+ item.getItemID() +"', "+ item.getItemAmount() +", "+ item.getItemAmount() * item.getItemPrice() +");");
            }
            return true;
        }else {
            return false;
        }
    }
}
