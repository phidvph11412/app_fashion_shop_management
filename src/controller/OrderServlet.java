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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/buy")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "buy":
                try {
                    saveOrderToDB(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

        }
    }

    private void saveOrderToDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();

        String name = (String) session.getAttribute("name");
        Order newOrder = (Order) session.getAttribute("newOrder");
        ArrayList<Order> oldOrder = orderService.getListOrderByName(name);

        if (newOrder == null){
            request.setAttribute("oldOrderList", oldOrder);
            request.setAttribute("message", "khong thanh cong!!");
            request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
        }
        for (Order order : oldOrder){
            for (Item item : newOrder.getListItem()){
                if (order.getItemId().equals(item.getItemID()) && order.getStatus().equals("NoProcess")){
                    order.setAmount(order.getAmount() + item.getItemAmount());
                   orderService.changeAmount(name, order.getItemId(), order.getAmount());
                }
            }
        }
       boolean isSaved = orderService.saveOrder(newOrder);
       if (isSaved){
           session.removeAttribute("newOrder");
           ArrayList<Order> oldOrder1 = orderService.getListOrderByName(name);
           request.setAttribute("oldOrderList", oldOrder1);
           request.setAttribute("message", "dat hang thanh cong ! thank you!!");
           request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
       }else {
           session.removeAttribute("newOrder");
           request.setAttribute("oldOrderList", oldOrder);
           request.setAttribute("message", "khong thanh cong!!");
           request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
       }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
