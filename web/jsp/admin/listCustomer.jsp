<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/register.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
<div class="row">
    <div class="container">
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
                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </nav>
            </div>
        </header>
        <nav class="navbar navbar-light bg-light">
            <form class="form-inline" method="post" action="/Customers">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" name="name" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" name="action" value="find" type="submit">Search</button>
            </form>
        </nav>
        <div class="container text-left">
            <a href="/Customers?action=showRegister" class="btn btn-success">Add New Customer</a>
        </div>
        <br>

        <table class="table table-success table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Password</th>
                <th>rePassword</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Address</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="customer" items="${customerList}">
                <tr>
                    <td><c:out value="${customer.getCustomerName()}"/></td>
                    <td><c:out value="${customer.getCustomerPassword()}"/></td>
                    <td><c:out value="${customer.getCustomerPassword()}"/></td>
                    <td><c:out value="${customer.getCustomerPhoneNumber()}"/></td>
                    <td><c:out value="${customer.getCustomerEmail()}"/></td>
                    <td><c:out value="${customer.getCustomerAddress()}"/></td>
                    <td>
                        <a href="/Customers?action=edit&name=${customer.customerName}"> Edit</a>
                        <br>
                        <a href="/Customers?action=delete&name=${customer.customerName}"> Delete </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>