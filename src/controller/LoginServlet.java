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
    String admin = "admin";
    String password = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "login" : login(request, response);
            break;
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customerList = customerService.getListUserAndPass();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        if (name.equals(admin) && pass.equals(password)){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/admin/item.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            requestDispatcher.forward(request, response);
        }
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
