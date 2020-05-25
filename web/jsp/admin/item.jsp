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
                    <span>${admin}</span>
                </div>
            </nav>
            <span>${message}</span>
        </div>
        <div class="col-lg-6">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/item-update">LIST ITEM <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/Customers">LIST CUSTOMER <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/order-update">LIST ORDER <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
    <hr>
    <div>
        <form action="/item-update" method="post">
            <input type="text" name="itemID" placeholder="ID" style="width: 100px">
            <input type="text" name="itemName" placeholder="Name">
            <input type="file" name="itemImage">
            <input type="text" name="itemPrice" placeholder="Price" style="width: 100px">
            <input type="text" name="itemAmount" placeholder="Amount" style="width: 100px">
            <select name="itemCategory" id="item-category">
                <option value="clothes">clothes</option>
                <option value="shoes">shoes</option>
                <option value="perfume">perfume</option>
            </select>
            <input type="text" name="itemDescribe" placeholder="Describe">
            <button type="submit" class="btn btn-primary" name="action" value="add">ADD</button>
            <button type="submit" class="btn btn-primary" name="action" value="edit">EDIT</button>
        </form>
    </div>
    <div>
        <form action="/item-update" method="get">
            <button> <</button>
            <button> ></button>
            Category : <select name="category" id="">
            <option value="all">all</option>
            <option value="clothes">clothes</option>
            <option value="shoes">shoes</option>
            <option value="perfume">perfume</option>
        </select>
            Price : <select name="price" id="">
            <option value="null"> no choose</option>
            <option value="low"> < 20</option>
            <option value="medium"> 20 - 50</option>
            <option value="hight"> > 50</option>
        </select>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="search">Search
            </button>
        </form>
    </div>

    <div class="content ">

        <table class="table table-success table-hover">
            <thead>
            <tr>
                <th>ItemID</th>
                <th>ItemName</th>
                <th>Image</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Category</th>
                <th>Describe</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><c:out value="${item.getItemID()}"/></td>
                    <td><c:out value="${item.getItemName()}"/></td>
                    <td><c:out value="${item.getItemImage()}"/></td>
                    <td><c:out value="${item.getItemPrice()}"/></td>
                    <td><c:out value="${item.getItemAmount()}"/></td>
                    <td><c:out value="${item.getItemCategory()}"/></td>
                    <td><c:out value="${item.getItemDescribe()}"/></td>
                    <td>
                        <br>
                        <a href="/item-update?action=delete&item=${item.getItemID()}"> Delete </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
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