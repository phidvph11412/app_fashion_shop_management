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
    <link rel="stylesheet" href="../css/register.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
<div class="row">
    <div class="container">
        <header class="header row">
            <div class="col-xl-6 logo">
                <h1><a href="/index.jsp">LOGO</a></h1>
            </div>
            <div class="col-xl-6 menu">
                <ul class="nav">
                    <li class="border-bottom-red"><a href="/index.jsp">HOME</a></li>
                    <li><a href="/jsp/login.jsp">LOGIN</a></li>
                    <li><a href="/Customers?action=create">REGISTER</a></li>
                    <li><a href="#footer-contact">CONTACT</a></li>
                    <li><a href="#"><span class="fa-stack ">
                  <i class="fa fa-circle-thin fa-stack-2x"></i>
                  <i class="fa fa-facebook fa-stack-1x"></i>
                </span></a></li>
                    <li><a href=""> <span class="fa-stack "><i class="fa fa-circle-thin fa-stack-2x"></i>
                    <i class="fa fa-youtube fa-stack-1x"></i>
                </span></a></li>
                </ul>
            </div>
        </header>
        <nav class="navbar navbar-light bg-light">
            <form class="form-inline" method="post" action="/Customers">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" name="name" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" name="action" value="find" type="submit">Search</button>
            </form>
        </nav>
        <div class="container text-left">
            <a href="/Customers?action=showRegister" class="btn btn-success">Add New User</a>
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
                        <a href="/Customers?action=editCustomer&name=${customer.customerName}"> Edit</a>
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