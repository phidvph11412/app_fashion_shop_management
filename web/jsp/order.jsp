<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>
    <link rel="stylesheet" href="../css/home.css">
    <link rel="stylesheet" href="../css/order.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container-fluid">
    <header class="header row">
        <div class="col-xl-4 logo">
            <h1><a href="../index.jsp">LOGO</a></h1>
        </div>
        <div class="col-xl-4"><span class="user-name"
                                    style="line-height: 90px;color: #eb6d6c">${name}</span></div>
        <div class="col-xl-4 menu">
            <ul>
                <li class="border-bottom-red"><a href="../index.jsp">HOME</a></li>
                <li><a href="../jsp/login.jsp">LOGIN</a></li>
                <li><a href="../jsp/register.jsp">REGISTER</a></li>
                <li><a href="../jsp/order.jsp">CART</a></li>
                <li class="icon"><a href="https://www.facebook.com/fc.ngoclich"><span class="fa-stack ">
                  <i class="fa fa-circle-thin fa-stack-2x"></i>
                  <i class="fa fa-facebook fa-stack-1x"></i>
                </span></a></li>
                <li class="icon"><a href=""> <span class="fa-stack "><i class="fa fa-circle-thin fa-stack-2x"></i>
                    <i class="fa fa-youtube fa-stack-1x"></i>
                </span></a></li>
            </ul>
        </div>
    </header>
    <div class="order-content row">
        <div class="list-item col-xl-9">
            <table class="table table-hover table-borderless ">
                <c:forEach items="${oldOrderList}" var="oldOrder">
                    <tr>
                        <td><p>ID : ${oldOrder.getItemId()}</p></td>
                        <td><p>Price : ${oldOrder.getPrice()}</p></td>
                        <td>SL :
                            <button value="" name="action">-</button>
                                ${oldOrder.getAmount()}
                            <button>+</button>
                        </td>
                        <td>Thanh tien :${oldOrder.getAmount() * oldOrder.getPrice()} </td>
                        <td>Trang thai :${oldOrder.getStatus()} </td>
                        <td>
                            <button>Xoa</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="pay col-xl-3">
            <form action="/buy" method="post">
                <table>
                    <tr>
                        <td colspan="4">Name : ${name} </td>
                    </tr>
                </table>
                <table class="table table-borderless">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Name</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${newOrder.getListItem()}" var="item">
                        <tr>
                            <td scope="row">item</td>
                            <td>${item.getItemName()}</td>
                            <td>${item.getItemAmount()}</td>
                            <td>${item.getItemAmount() * item.getItemPrice()}</td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="2">Tong tien</td>
                        <c:forEach items="${newOrder.getListItem()}" var="item">
                            <c:set var="sum" value="${sum+ (item.getItemAmount() * item.getItemPrice()) }"/>
                        </c:forEach>
                        <td colspan="1"><c:out value="${sum}"/></td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <button type="submit" name="action" value="buy">BUY</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <span>${message}</span>
        </div>
        <div class="cart">
            <table class="table table-hover table-borderless ">
                <c:forEach items="${newOrder.getListItem()}" var="item">
                    <tr>
                        <td>${item.getItemID()}</td>
                        <td>${item.getItemName()}</td>
                        <td>${item.getItemImage()}</td>
                        <td>${item.getItemAmount()}</td>
                        <td>${item.getItemPrice()}</td>
                        <td>${item.getItemCategory()}</td>
                        <td>${item.getItemDescribe()}</td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>


    <hr>


    <div class="contact" id="footer-contact">
        <h2>CONTACT</h2>
        <div class="contact-content row">
            <div class="my-information col-xl-6">
                Hanoi, Vietnam<br>
                999-7777-000<br>
                daovanphi25051994@gmail.com
            </div>
            <div class="customer-information col-xl-6">
                <form action="#" method="post">
                    <input id="customer-name" type="text" name="name" placeholder="Name">
                    <input id="customer-phone" type="text" name="phoneNumber" placeholder="Phone"><br>
                    <input id="customer-email" type="text" name="emailAddress" placeholder="Email Address"><br>
                    <textarea id="customer-contact-content" name="contactContent"
                              placeholder="Message"></textarea><br>
                    <input id="btn-contact" type="submit" value="CONTACT US">
                </form>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="footer-menu">
            <ul>
                <li class="border-bottom-red"><a href="/index.jsp">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Shop</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div>
        <div class="copy-right">
            <p>Copyrigh 2020 All rights reserved - phi dao <br> Powered By SITE123-Website creator</p>
        </div>
    </div>

</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="../js/home.js"></script>
</body>
</html>

