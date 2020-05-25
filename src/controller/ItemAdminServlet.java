package controller;

import model.Item;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ItemAdminServlet", urlPatterns = "/item-update")
public class ItemAdminServlet extends HttpServlet {
    ItemService itemService = new ItemService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleteItem(request, response);
                break;
            case "edit":
                editItem(request, response);
                try {
                    showListItem(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "add":
                try {
                    addItem(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                try {
                    showListItem(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
    }

    private void showListItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ResultSet items = itemService.getListItem();
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);

    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String itemID = request.getParameter("itemID");
        String itemName = request.getParameter("itemName");
        String itemImage = request.getParameter("itemImage");
        float itemPrice = Float.valueOf(request.getParameter("itemPrice"));
        int itemAmount = Integer.valueOf(request.getParameter("itemAmount"));
        String itemCategory = request.getParameter("itemCategory");
        String itemDescribe = request.getParameter("itemDescribe");
        Item item = new Item(itemID, itemName, itemImage, itemPrice, itemAmount, itemCategory, itemDescribe);
        boolean isSaved = itemService.saveDataItem(item);
        if (isSaved) {
            request.setAttribute("message", "save successfully");
            showListItem(request, response);
        } else {
            request.setAttribute("message", "save not successfully");
            showListItem(request, response);
        }

    }

    private void editItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemID = request.getParameter("itemID");
        String itemName = request.getParameter("itemName");
        String itemImage = request.getParameter("itemImage");
        float itemPrice = Float.valueOf(request.getParameter("itemPrice"));
        int itemAmount = Integer.valueOf(request.getParameter("itemAmount"));
        String itemCategory = request.getParameter("itemCategory");
        String itemDescribe = request.getParameter("itemDescribe");
        Item item = new Item(itemID, itemName, itemImage, itemPrice, itemAmount, itemCategory, itemDescribe);
        boolean isEdited = itemService.editItemByID(itemID, item);
        if (isEdited) {
            request.setAttribute("message", "edit successfully");
        } else {
            request.setAttribute("message", "edit not successfully");
        }
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemID = request.getParameter("item");
        boolean isDeleted = itemService.deleteItemByID(itemID);
        if (isDeleted) {
            request.setAttribute("message", "delete successfully");
        } else {
            request.setAttribute("message", "delete not successfully . item already exists in shopping cart");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "search":
                try {
                    searchItem(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "delete":
                deleteItem(request, response);
                try {
                    showListItem(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "edit":
                editItem(request, response);
                break;
            default:
                try {
                    showListItem(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }

    }

    private void searchItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        if (category.equals("all") && price.equals("null")) {
            showListItem(request, response);
        } else if (category.equals("all") && price.equals("low")) {
            showListItemLowPrice(request, response);
        } else if (category.equals("all") && price.equals("medium")) {
            showListItemMediumPrice(request, response);
        } else if (category.equals("all") && price.equals("hight")) {
            showListItemHightPrice(request,response);
        } else if (category.equals("clothes") && price.equals("null")) {
            showListClothes(request, response);
        } else if (category.equals("clothes") && price.equals("low")) {
            showListClothesLowPrice(request, response);
        } else if (category.equals("clothes") && price.equals("medium")) {
            showListClothesMediumPrice(request, response);
        } else if (category.equals("clothes") && price.equals("hight")) {
            showListClothesHightPrice(request, response);
        } else if (category.equals("shoes") && price.equals("null")) {
            showListShoes(request, response);
        } else if (category.equals("shoes") && price.equals("low")) {
            showListShoesLowPrice(request, response);
        } else if (category.equals("shoes") && price.equals("medium")) {
            showListShoesMediumPrice(request, response);
        } else if (category.equals("shoes") && price.equals("hight")) {
            showListShoesHightPrice(request, response);
        } else if (category.equals("perfume") && price.equals("null")) {
            showListPerfume(request, response);
        } else if (category.equals("perfume") && price.equals("low")) {
            showListPerfumeLowtPrice(request, response);
        } else if (category.equals("perfume") && price.equals("medium")) {
            showListPerfumeMediumPrice(request, response);
        } else if (category.equals("perfume") && price.equals("hight")) {
            showListPerfumeHightPrice(request, response);
        }

    }

    private void showListItemHightPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByPrice(50, 1000);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListItemMediumPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByPrice(20, 50);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListItemLowPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByPrice(0, 20);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListPerfume(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategory("perfume");
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListShoes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategory("shoes");
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListClothes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategory("clothes");
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListPerfumeLowtPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("perfume", 0, 20);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListPerfumeMediumPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("perfume", 20, 50);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListPerfumeHightPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("perfume", 50, 1000);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }

    private void showListClothesLowPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("clothes", 0, 20);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }
    private void showListClothesMediumPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("clothes", 20, 50);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }
    private void showListClothesHightPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("clothes", 50, 1000);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }
    private void showListShoesLowPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        ResultSet items = itemService.getListItemByCategoryAndPrice("shoes", 0, 20);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }
    private void showListShoesMediumPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("shoes", 20, 50);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }
    private void showListShoesHightPrice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ResultSet items = itemService.getListItemByCategoryAndPrice("shoes", 50, 1000);
        ArrayList<Item> listItems = new ArrayList<>();
        while (items.next()) {
            String itemId = items.getString(1);
            String name = items.getString(2);
            String img = items.getString(3);
            float price1 = items.getFloat(4);
            int amount = items.getInt(5);
            String cate = items.getString(6);
            String describe = items.getString(7);
            listItems.add(new Item(itemId, name, img, price1, amount, cate, describe));
        }
        request.setAttribute("items", listItems);
        request.getRequestDispatcher("jsp/admin/item.jsp").forward(request, response);
    }
}
