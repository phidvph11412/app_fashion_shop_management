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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletCustomer", urlPatterns = "/Customers")

public class RegisterServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    insertCustomer(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "edit":
                try {
                    update(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "find":
                try {
                    search(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                try {
                    listCustomer(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCustomer(request, response);
                break;
            case "edit":
                showEditCustomer(request, response);
                break;
            case "delete":
                try {
                    deleteCustomer(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "showRegister":
                showRegister(request, response);
                break;
            default:
                try {
                    listCustomer(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Customer> customerList = customerService.selectAllCustomer();
        request.setAttribute("customerList", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/admin/listCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void showCustomer(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/editCustomer.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showRegister(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/register.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ValidateCustomer validate = new ValidateCustomer();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String rePass = request.getParameter("repass");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if (validate.validateName(name) && validate.validatePass(pass) && validate.validateRepass(rePass) && validate.validatePhone(phone) &&
                validate.validateEmail(email) && validate.validateAddress(address) && pass.equals(rePass)) {
            Customer customer = new Customer(name, pass, phone, email, address);
            customerService.insertCustomers(customer);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/register.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/Error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void showEditCustomer(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Customer customer = customerService.selectCustomer(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/editCustomer.jsp");
        request.setAttribute("customers", customer);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String customerName = request.getParameter("name");
        customerService.deleteCustomer(customerName);
        List<Customer> listCustomer = customerService.selectAllCustomer();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/register.jsp");
        dispatcher.forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        List<Customer> customers = customerService.searchCustomer(name);
        request.setAttribute("customerList", customers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/admin/listCustomer.jsp");
        requestDispatcher.forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ValidateCustomer validate = new ValidateCustomer();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        if (validate.validatePass(pass) && validate.validatePhone(phone)
                && validate.validateEmail(email) && validate.validateAddress(address)) {
            Customer customer = new Customer(name, pass, phone, email, address);
            boolean isUpdate = customerService.updateCustomer(customer);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/editCustomer.jsp");
            HttpSession session = request.getSession();
            if (isUpdate) {
                session.setAttribute("title1", "Edit success");
                try {
                    requestDispatcher.forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                session.setAttribute("title1", "Edit no success");
                try {
                    requestDispatcher.forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/Error.jsp");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("title1", "Edit no success");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
