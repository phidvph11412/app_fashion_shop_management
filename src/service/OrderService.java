package service;

import model.Item;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    DAL dal;
    private String url = "jdbc:mysql://localhost:3306/lucy_shop";
    private String user = "root";
    private String pass = "";
    private static final String SELECT_ODER = "select customerName  ,itemId ,amount ,status  from orders";
    private static final String UPDATE_ORDER = "update orders set  amount = ?  ,status = ? where customerName = ?  and itemId = ?";
    private static final String SELECT_ODER_NAME = "select customerName , itemId , amount,status from orders where customerName = ? and itemId = ?";
    private static final String DELETE_ODER = "delete from orders where customerName = ? and itemId =? ";
    private static final String GET_ORDER_BY_ID = "select * from orders where customerName = ?";
    private static final String UPDATE_AMOUNT = "update orders set amount = ? where customerName = ?  and itemId = ? ";



    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            printSQLException(throwables);
        }
        return connection;
    }

    public OrderService() {
        dal = new DAL();
    }

    public boolean saveOrder(Order order) {
        if (order == null){
            return false;
        }
        ArrayList<Item> listItem = order.getListItem();
        if (listItem.size() >= 1) {
            for (Item item : listItem) {
                dal.updateData("insert into orders(customerName, itemId, amount, price) " +
                        "values('" + order.getCustomerName() + "','" + item.getItemID() + "', " + item.getItemAmount() + ", " + item.getItemAmount() * item.getItemPrice() + ");");
            }
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean updateOrder(Order order) throws SQLException {
        boolean rowUpdate = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setString(3, order.getCustomerName());
            preparedStatement.setString(4, order.getItemId());
            preparedStatement.setInt(1, order.getAmount());
            preparedStatement.setString(2, order.getStatus());
            rowUpdate = preparedStatement.executeUpdate() > 0;
            System.out.println(preparedStatement);
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return rowUpdate;
    }

    @Override
    public List<Order> getListOder() throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ODER);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String customerName = resultSet.getString("customerName");
            String itemId = resultSet.getString("itemId");
            int amount = resultSet.getInt("amount");
            String status = resultSet.getString("status");
            orderList.add(new Order(customerName, itemId, amount, status));
        }
        return orderList;
    }

    @Override
    public Order selectOder(String customerName, String itemId) throws SQLException {
        Order order = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ODER_NAME);
        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, itemId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("customerName");
            String item = resultSet.getString("itemId");
            int amount = Integer.parseInt(resultSet.getString("amount"));
            String status = resultSet.getString("status");
            order = new Order(name, item, amount, status);
        }
        return order;
    }

    @Override
    public boolean deleteOder(String customerName, String itemId) throws SQLException {
        boolean rowDelete;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ODER);
        preparedStatement.setString(1, customerName);
        preparedStatement.setString(2, itemId);
        rowDelete = preparedStatement.executeUpdate() > 0;
        System.out.println(preparedStatement);
        return rowDelete;
    }

    public ArrayList<Order> getListOrderByName(String customerName) throws SQLException {
        ArrayList<Order> listOrder = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
        preparedStatement.setString(1, customerName);
        ResultSet orders = preparedStatement.executeQuery();
        while (orders.next()) {
            String name = orders.getString(1);
            String id = orders.getString(2);
            int amount = orders.getInt(3);
            float price = orders.getFloat(4);
            String status = orders.getString(5);
            listOrder.add(new Order(name, id, amount, price, status));
        }
        System.out.println(preparedStatement);
        return listOrder;
    }

    @Override
    public List<Order> search(String name) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = getConnection();
        String sql = "{call getOderByStatus(?)}";
        CallableStatement statement = connection.prepareCall(sql);
        String name1 = "%" + name + "%";
        statement.setString(1, name1);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String customerName = resultSet.getString("customerName");
            String item = resultSet.getString("itemId");
            int amount = resultSet.getInt("amount");
            String status = resultSet.getString("status");
            orderList.add(new Order(customerName, item, amount, status));
        }
        return orderList;
    }

    @Override
    public Boolean changeAmount(String name, String id , int amount) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AMOUNT);
        preparedStatement.setInt(1, amount);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, id);
        return preparedStatement.executeUpdate() > 0 ? true : false;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = e.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
