package controller;

import model.Order;
import service.OrderService;
import validate.ValidateItem;

import javax.print.attribute.standard.OrientationRequested;
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

@WebServlet(name = "OrderAdminServlet", urlPatterns = "/order-update")
public class OrderAdminServlet extends HttpServlet {
    OrderService orderService = new OrderService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                try {
                    Update(request, response);
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
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                try {
                    ShowEditOder(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "delete":
                try {
                    delete(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                try {
                    ListOder(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }

    private void Update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ValidateItem validateItem = new ValidateItem();
        String name = request.getParameter("name");
        String item = request.getParameter("itemId");
        String amount = request.getParameter("amount");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        if (validateItem.validateAmount(amount) ) {
            int amount1 = Integer.parseInt(amount);
            Order order = new Order(name, item, amount1, status);
            boolean isUpdated = orderService.updateOrder(order);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/editOrder.jsp");
            if (isUpdated) {
                request.setAttribute("title", "Edit  success");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("title", "Edit no success ");
                requestDispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/editOrder.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("title", "Edit no success ");
            requestDispatcher.forward(request, response);
        }
    }

    private void ListOder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Order> orderList = orderService.getListOder();
        request.setAttribute("orderList", orderList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/listOder.jsp");
        requestDispatcher.forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        List<Order> orderList = orderService.search(name);
        request.setAttribute("orderList", orderList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/listOder.jsp");
        requestDispatcher.forward(request, response);
    }

    private void ShowEditOder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        String item = request.getParameter("item");
        Order order = orderService.selectOder(name, item);
        System.out.println(order.getCustomerName());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/editOrder.jsp");
        request.setAttribute("orders", order);
        requestDispatcher.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        String item = request.getParameter("item");
        orderService.deleteOder(name, item);
        List<Order> orderList = new ArrayList<>();
        request.setAttribute("order", orderList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/editOrder.jsp");
        requestDispatcher.forward(request, response);
    }
}
