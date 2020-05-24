<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 5/19/20
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
    <link rel="stylesheet" href="../css/register.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container">
    <header class="header row">
        <div class="col-xl-6 logo">
            <h1><a href="/index.jsp">LOGO</a></h1>
        </div>
        <div class="col-xl-6 menu">
            <ul class="nav">
                <li class="border-bottom-red"><a href="/index.jsp">HOME</a></li>
                <li><a href="/jsp/login.jsp">LOGIN</a></li>
                <li><a href="/jsp/register.jsp">REGISTER</a></li>
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
    <main class="content">
        <form method="post" action="/order-update" class="form-group form">
            <h2 class="text-primary ">Edit Order</h2>
            <div class="form-group input-form ">
                <label> Name :</label>
                <input type="text" name="name" value="${orders.getCustomerName()}" class=" form-control ">
            </div>
            <div class="form-group input-form ">
                <label>ItemId :</label>
                <input type="text" name="itemId" value="${orders.getItemId()}" class="form-control ">
            </div>
            <div class="form-group input-form ">
                <label>Amount : </label>
                <input type="text" name="amount" value="${orders.getAmount()}" class="form-control ">
            </div>
            <div class="form-group input-form ">
                <label>Status :</label>
                <select name="status">
                    <option value="NoProcess">NoProcess</option>
                    <option value="Process">Process</option>
                    <option  value="Processed">Processed</option>
                </select>
                <%--                <input type="text" name="status" value="${orders.getStatus() }" class="form-control ">--%>
            </div>
            <center style="margin-top: 50px">
                <button style="width: 100px" class="btn btn-primary "
                        type="submit" name="action" value="edit">Edit
                </button>
                <button style="width: 100px" class="btn btn-primary"><a href="/order-update" style="color: yellow ;">Back</a>
                </button>
            </center>
            <br>
            <center style="margin-top: 50px;color: brown">
                <h1><span>${title}</span></h1>
            </center>
        </form>
    </main>

    <div class="contact " id="footer-contact">
        <h2>CONTACT</h2>
        <div class="contact-content row">
            <div class="my-information col-xl-6">
                Hanoi, Vietnam<br>
                999-7777-000<br>
                daovanphi25051994@gmail.com
                lebadat1996ptit@gmail.com
            </div>
            <div class="customer-information col-xl-6">
                <form action="#" method="">
                    <input id="customer-name" type="text" name="name" placeholder="Name">
                    <input id="customer-phone" type="text" name="phoneNumber" placeholder="Phone"><br>
                    <input id="customer-email" type="text" name="emailAddress" placeholder="Email Address"><br>
                    <textarea id="customer-contact-content" name="contactContent" placeholder="Message"></textarea><br>
                    <input id="btn-contact" type="submit" value="CONTACT US">
                </form>
            </div>
        </div>
    </div>
    <footer class="footer">
        <div class="footer-menu">
            <ul>
                <li class="border-bottom-red"><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Shop</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div>
        <div class="copy-right">
            <p>Copyrigh 2020 All rights reserved - phi dao <br> Powered By SITE123-Website creator</p>
        </div>
    </footer>


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
</body>
</html>