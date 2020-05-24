package service;

import model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderService {
    public boolean saveOrder(Order order);

    boolean updateOrder(Order order) throws SQLException;

    public List<Order> getListOder() throws SQLException;

    Order selectOder(String customerName, String itemId) throws SQLException;

    boolean deleteOder(String customerName, String itemId) throws SQLException;

    List<Order> search(String name) throws SQLException;

    Boolean changeAmount(String name, String id, int amount) throws SQLException;
}
