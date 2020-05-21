package controller;

import model.Item;
import model.Order;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/buy")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        OrderService orderService = new OrderService();
        switch (action) {
            case "buy":
                HttpSession session = request.getSession();
                Order order = (Order) session.getAttribute("order");
                orderService.saveOrder(order);
                request.setAttribute("message", "dat hang thanh cong ! thank you!!");
                request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
