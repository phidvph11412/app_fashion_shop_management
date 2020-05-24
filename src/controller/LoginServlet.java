package controller;

import model.Customer;
import model.Order;
import service.CustomerService;
import service.OrderService;
import validate.ValidateCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    CustomerService customerService = new CustomerService();
    OrderService orderService = new OrderService();
    String admin = "admin";
    String password = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login1":
                try {
                    loginUser(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Customer> customerList = customerService.getListUserAndPass();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        if (name.equals(admin) && pass.equals(password)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/listOder.jsp");
            List<Order> orderList = new ArrayList<>();
            orderList = orderService.getListOder();
            request.setAttribute("orderList", orderList);
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            requestDispatcher.forward(request, response);
            return;
        }
        for (Customer customer : customerList) {
            if (name.equals(customer.getCustomerName()) && pass.equals(customer.getCustomerPassword())) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("name", name);
                requestDispatcher.forward(request, response);
                return;
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/Error.jsp");
        requestDispatcher.forward(request, response);
        return;
    }
}