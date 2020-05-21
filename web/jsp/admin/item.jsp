<%@ page import="java.sql.ResultSet" %>
<%@ page import="service.ItemService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <header class="row">
        <div class="col-lg-6">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="/index.jsp">LOGO</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="/index.jsp">HOME <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="/index.jsp">SINGOUT <span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <span>${message}</span>
        </div>
        <div class="col-lg-6">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/index.jsp">LIST ITEM <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/index.jsp">LIST CUSTOMER <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/index.jsp">LIST ORDER <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </nav>
        </div>
    </header>
    <hr>
    <div class="content row">
        <div class="col-xl-9 col-lg-8 col-md-7 col-sm-6 col-6 row">
            <% ItemService dbItem = new ItemService();
                ResultSet listItem = dbItem.getListItem();%>
            <% while (listItem.next()) {%>
            <div class="col-xl-3 col-lg-4 col-md-6 col-12">
                <form action="/item-update" method="post">
                    <div class="card" style="width: 16rem;">
                        <img src="../../img/<%=listItem.getString(3)%>" class="card-img-top" width="15rem"; height="200px" alt="">
                        <div class="card-body">
                            <input type="file" name="itemImage"/>
                            Item ID : <input type="text" name="itemID" value="<%=listItem.getString(1)%>"
                                             class="card-title"/>
                            Item Name : <input type="text" name="itemName" value="<%=listItem.getString(2)%>"
                                               class="card-title"/>
                            Item Price : <input type="text" name="itemPrice" value="<%=listItem.getString(4)%>"
                                                class="card-title"/>
                            Item Amount : <input type="text" name="itemAmount" value="<%=listItem.getString(5)%>"
                                                 class="card-title"/>
                            Item Category : <input type="text" name="itemCategory" value="<%=listItem.getString(6)%>"
                                                   class="card-title"/>
                            Item Describes : <input type="text" name="itemDescribe" value="<%=listItem.getString(7)%>"
                                                    class="card-title"/>
                            <button type="submit" name="action" value="delete" class="btn btn-primary">Delete</button>
                            <button type="submit" name="action" value="edit" class="btn btn-primary">Edit</button>
                        </div>
                    </div>
                </form>
            </div>
            <%}%>

        </div>
        <div class="col-xl-3 col-lg-4 col-md-5 col-sm-6 col-6">
            <form action="/item-update" method="post">
                <div class="form-group">
                    <label for="item-id">Item ID</label>
                    <input type="text" name="itemID" class="form-control" id="item-id">
                </div>
                <div class="form-group">
                    <label for="item-name">Item Name</label>
                    <input type="text" name="itemName" class="form-control" id="item-name">
                </div>
                <div class="form-group">
                    <label for="item-image">Item Image</label>
                    <input type="file" name="itemImage" class="form-control" id="item-image">
                </div>
                <div class="form-group">
                    <label for="item-price">Item Price</label>
                    <input type="text" name="itemPrice" class="form-control" id="item-price">
                </div>
                <div class="form-group">
                    <label for="item-amount">Item Amount</label>
                    <input type="text" name="itemAmount" class="form-control" id="item-amount">
                </div>
                <div class="form-group">
                    <label for="item-category">Item Category</label>
                    <select name="itemCategory" id="item-category">
                        <option value="clothes">clothes</option>
                        <option value="shoes">shoes</option>
                        <option value="perfume">perfume</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="item-describe">Item Describes</label>
                    <textarea name="itemDescribe" id="item-describe" class="form-control" cols="36" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary" name="action" value="add">Add Item</button>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>