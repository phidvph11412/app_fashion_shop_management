package service;

import java.sql.*;

public class DAL {
    Connection connection;
    Statement statement;

    public DAL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucy_shop", "root", "password");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getData(String sql) {
        statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean updateData(String sql) {
        statement = null;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(sql) > 0 ? true : false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}

