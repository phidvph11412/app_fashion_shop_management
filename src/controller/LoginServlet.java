package controller;

import model.Customer;
import service.CustomerService;
import validate.ValidateCustomer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    public CustomerService customerService = new CustomerService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = customerService.getListUserAndPass();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        for (Customer customer : customerList) {
            if (name.equals(customer.getCustomerName()) && pass.equals(customer.getCustomerPassword())) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("name", name);
                requestDispatcher.forward(request, response);
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/Error.jsp");
        requestDispatcher.forward(request, response);
    }
}
