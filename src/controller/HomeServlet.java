package controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import model.Item;
import model.Order;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemService itemService = new ItemService();
        HttpSession session = request.getSession();
        int defaultQuantity = 1;
        int defaultId = 1;

        if (session.getAttribute("name") == null) {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        } else {
            if (session.getAttribute("order") == null) {
                String id = request.getParameter("itemID");
                ResultSet item = itemService.getItemByID(id);
                try {
                    ArrayList<Item> listItem = new ArrayList<>();
                    item.next();
                    listItem.add(new Item(item.getString(1), item.getString(2), item.getString(3), item.getFloat(4), defaultQuantity, item.getString(6), item.getString(7)));
                    String name = (String) session.getAttribute("name");
                    Order order = new Order(defaultId, name, listItem);
                    session.setAttribute("order", order);
                    request.getRequestDispatcher("jsp/order.jsp").forward(request, response);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                Order order = (Order) session.getAttribute("order");
                ArrayList<Item> listItem = order.getListItem();
                String id = request.getParameter("itemID");
                boolean isExist = false;
                for (Item item : listItem) {
                    if (item.getItemID().equals(id)) {
                        item.setItemAmount(item.getItemAmount() + defaultQuantity);
                        request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
                        isExist = true;
                    }
                }

                if (!isExist) {
                    ResultSet item = itemService.getItemByID(id);
                    try {
                        item.next();
                        listItem.add(new Item(item.getString(1), item.getString(2), item.getString(3), item.getFloat(4), defaultQuantity, item.getString(6), item.getString(7)));
                        session.setAttribute("order", order);
                        request.getRequestDispatcher("jsp/order.jsp").forward(request, response);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("list");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "1" : try {
                showFirstList(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            break;
            case "2":
                try {
                    showSecondList(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }

    private void showSecondList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ItemService itemService = new ItemService();
        ResultSet items = itemService.getListItem();
        ArrayList<Item> listItem = new ArrayList<>();
        int count = 0;
        while (items.next()) {
            if (count >= 12 && count <= 24) {
                String id = items.getString(1);
                String name = items.getString(2);
                String img = items.getString(3);
                float price = items.getFloat(4);
                int amount = items.getInt(5);
                String cate = items.getString(6);
                String describe = items.getString(7);
                listItem.add(new Item(id, name, img, price, amount, cate, describe));
            }
            count++;
        }

        request.setAttribute("itemList", listItem);
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);

    }

    private void showFirstList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ItemService itemService = new ItemService();
        ResultSet items = itemService.getListItem();
        ArrayList<Item> listItem = new ArrayList<>();
        int count = 0;
        while (items.next()) {
            String id = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItem.add(new Item(id, name, img, price, amount, cate, describe));
            count++;
            if (count == 12) {
                break;
            }
        }

        request.setAttribute("itemList", listItem);
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }
}
