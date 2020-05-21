package service;

import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
    public List<Customer> selectAllCustomer();

    void insertCustomers(Customer customer) throws SQLException;

    Customer selectCustomer(String customerName);

    boolean updateCustomer(Customer customer) throws SQLException;

    boolean deleteCustomer(String customerName) throws SQLException;

    List<Customer> searchCustomer(String customerName) throws SQLException;

    public List<Customer> getListUserAndPass();
}
