package controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import model.Item;
import model.Order;
import service.ItemService;
import service.OrderService;

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
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add-to-cart":
                try {
                    addItemToCart(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }

    }

    private void addItemToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ItemService itemService = new ItemService();
        HttpSession session = request.getSession();
        int defaultQuantity = 1;
        int defaultId = 1;
        String name = (String) session.getAttribute("name");

        if (session.getAttribute("name") == null) {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            return;
        } else {
            if (session.getAttribute("newOrder") == null) {
                String id = request.getParameter("itemID");
                ResultSet item = itemService.getItemByID(id);
                try {
                    ArrayList<Item> listItem = new ArrayList<>();
                    item.next();
                    listItem.add(new Item(item.getString(1), item.getString(2), item.getString(3), item.getFloat(4), defaultQuantity, item.getString(6), item.getString(7)));
                    Order order = new Order(defaultId, name, listItem);
                    //return new cart
                    session.setAttribute("newOrder", order);
                    //get old cart
                    OrderService orderService = new OrderService();
                    ArrayList<Order> oldOrderList = orderService.getListOrderByName(name);
                    request.setAttribute("oldOrderList", oldOrderList);
                    request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
                    return;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                Order order = (Order) session.getAttribute("newOrder");
                ArrayList<Item> listItem = order.getListItem();
                String id = request.getParameter("itemID");
                boolean isExist = false;
                for (Item item : listItem) {
                    if (item.getItemID().equals(id)) {
                        item.setItemAmount(item.getItemAmount() + defaultQuantity);
                        //get old cart
                        OrderService orderService = new OrderService();
                        ArrayList<Order> oldOrderList = orderService.getListOrderByName(name);
                        request.setAttribute("oldOrderList", oldOrderList);
                        request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
                        isExist = true;
                    }
                }

                if (!isExist) {
                    ResultSet item = itemService.getItemByID(id);
                    try {
                        item.next();
                        listItem.add(new Item(item.getString(1), item.getString(2), item.getString(3), item.getFloat(4), defaultQuantity, item.getString(6), item.getString(7)));
                        session.setAttribute("newOrder", order);
                        //get old cart
                        OrderService orderService = new OrderService();
                        ArrayList<Order> oldOrderList = orderService.getListOrderByName(name);
                        request.setAttribute("oldOrderList", oldOrderList);
                        request.getRequestDispatcher("jsp/order.jsp").forward(request, response);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "1":
                try {
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
            case "logout":
                deleteSession(request, response);
                break;
            case "clothes":
                try {
                    showListClothes(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "shoes":
                try {
                    showListShoes(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "perfume":
                try {
                    showListPerfume(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "cart":
                try {
                    showCartByName(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
    }

    private void showListShoes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String category = request.getParameter("action");
        ItemService itemService = new ItemService();
        ResultSet listItem = itemService.getListItemByCategory(category);
        ArrayList<Item> listClothes = new ArrayList<>();
        while (listItem.next()) {
            String id = listItem.getString(1);
            String name = listItem.getString(2);
            String image = listItem.getString(3);
            float price = listItem.getFloat(4);
            int amount = listItem.getInt(5);
            String category1 = listItem.getString(6);
            String describe = listItem.getString(7);
            listClothes.add(new Item(id, name, image, price, amount, category1, describe));

        }
        request.setAttribute("itemList", listClothes);
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    private void showListPerfume(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String category = request.getParameter("action");
        ItemService itemService = new ItemService();
        ResultSet listItem = itemService.getListItemByCategory(category);
        ArrayList<Item> listClothes = new ArrayList<>();
        while (listItem.next()) {
            String id = listItem.getString(1);
            String name = listItem.getString(2);
            String image = listItem.getString(3);
            float price = listItem.getFloat(4);
            int amount = listItem.getInt(5);
            String category1 = listItem.getString(6);
            String describe = listItem.getString(7);
            listClothes.add(new Item(id, name, image, price, amount, category1, describe));

        }
        request.setAttribute("itemList", listClothes);
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    private void showListClothes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String category = request.getParameter("action");
        ItemService itemService = new ItemService();
        ResultSet listItem = itemService.getListItemByCategory(category);
        ArrayList<Item> listClothes = new ArrayList<>();
        while (listItem.next()) {
            String id = listItem.getString(1);
            String name = listItem.getString(2);
            String image = listItem.getString(3);
            float price = listItem.getFloat(4);
            int amount = listItem.getInt(5);
            String category1 = listItem.getString(6);
            String describe = listItem.getString(7);
            listClothes.add(new Item(id, name, image, price, amount, category1, describe));

        }
        request.setAttribute("itemList", listClothes);
        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    private void showCartByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if (name == null) {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
        } else {
            // show old order and new order if exist
            OrderService orderService = new OrderService();
            ArrayList<Order> listOrder = orderService.getListOrderByName(name);
            request.setAttribute("oldOrderList", listOrder);
            request.getRequestDispatcher("/jsp/order.jsp").forward(request, response);
        }

    }

    private void deleteSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        session.removeAttribute("newOrder");
        request.getRequestDispatcher("/index.jsp").forward(request, response);

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
