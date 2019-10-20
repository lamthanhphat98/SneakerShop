<%-- 
    Document   : admin
    Created on : Oct 3, 2019, 5:57:41 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN PAGE</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>

        <div id="background">
            <div id="page">
                <div id="header">
                    <div id="logo">
                        <a href="register.jsp"><img src="images/logo.png" alt="LOGO"></a>
                    </div>
                    <div id="navigation">

                        <ul id="secondary">


                            <c:if test="${sessionScope.ADMIN != null}">
                                <li> Welcome Admin, ${sessionScope.ADMIN}  </li>
                                </c:if>   
                            <li> <a href="MainController?action=logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
                <div class="content">

                    <h1>Hãy cùng xem thống kê của những ngày qua nào</h1>
                    <c:if test="${sessionScope.ADMIN == 'saigonsneakeradmin'}" >

                        <ul style="list-style: none">
                            <li>
                                <h3>Số lượng người xem giày của shop bạn</h3>
                                <iframe width="150" height="150" src="https://datastudio.google.com/embed/reporting/0bf7f45d-193b-465a-b8bd-e52a2941a3a9/page/B411" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>
                            <li>
                                <h3>Top những đôi giày có lượt xem cao nhất</h3>
                                <iframe width="500" height="250" src="https://datastudio.google.com/embed/reporting/b613d33f-2560-4573-9633-285f894e080e/page/3M31" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>

                        </ul>
                        <form action="MainController" method="post">

                            <input type="submit" value="Export PDF" name="action"/>
                        </form>
                        <form action="MainController" method="post">
                            <input type="submit" value="Crawl" name="action"/>
                        </form>

                    </c:if>
                    <c:if test="${sessionScope.ADMIN == 'kingshoesadmin'}" >

                        <ul style="list-style: none">
                            <li>
                                <h3>Số lượng người xem giày của shop bạn</h3>
                                <iframe width="150" height="100" src="https://datastudio.google.com/embed/reporting/17edf00e-14d1-4c20-b1d8-aa140069ed36/page/4LB3" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>
                            <li>
                                <h3>Top những đôi giày có lượt xem cao nhất</h3>
                                <iframe width="500" height="250" src="https://datastudio.google.com/embed/reporting/919be4f4-3400-4ce0-a784-04da7c136ebc/page/U5c2" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>
                        </ul> 
                        <form action="MainController" method="post">

                            <input type="submit" value="Export PDF" name="action"/>
                        </form>
                        <form action="MainController" method="post">
                            <input type="submit" value="Crawl" name="action"/>
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.ADMIN == 'centimetadmin'}" >

                        <ul style="list-style: none">
                            <li>
                                <h3>Số lượng người xem giày của shop bạn</h3>
                                <iframe width="150" height="100" src="https://datastudio.google.com/embed/reporting/ae046778-8760-4149-abe7-479a5d69f12e/page/S1w2" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>
                            <li>
                                <h3>Top những đôi giày có lượt xem cao nhất</h3>
                                <iframe width="500" height="250" src="https://datastudio.google.com/embed/reporting/6a99b982-982d-4a80-b048-0e483e894ec1/page/TNx2" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>

                        </ul>
                        <form action="MainController" method="post">
                            <input type="submit" value="Export PDF" name="action"/>
                        </form>
                        <form action="MainController" method="post">
                            <input type="submit" value="Crawl" name="action"/>
                        </form>
                   
                    </c:if>
                      <c:if test="${sessionScope.ADMIN == 'hoangphucadmin'}" >

                        <ul style="list-style: none">
                            <li>
                                <h3>Số lượng người xem giày của shop bạn</h3>
                                <iframe width="150" height="100" src="https://datastudio.google.com/embed/reporting/96b4cc75-d6ca-4f83-bb8a-60cd7503584e/page/3e12" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>
                            <li>
                                <h3>Top những đôi giày có lượt xem cao nhất</h3>
                                <iframe width="500" height="250" src="https://datastudio.google.com/embed/reporting/6a99b982-982d-4a80-b048-0e483e894ec1/page/TNx2" frameborder="0" style="border:0" allowfullscreen></iframe>
                            </li>
                        </ul>
                        <form action="MainController" method="post">
                            <input type="submit" value="Export PDF" name="action"/>
                        </form>
                        <form action="MainController" method="post">
                            <input type="submit" value="Crawl" name="action"/>
                        </form>                  
                    </c:if>

                </div>
                <div id="footer">
                    <div class="background">
                        <div id="connect">
                            <h5>Get Social With us!</h5>
                            <ul>
                                <li>
                                    <a href="http://techcomsoft.vn/go/facebook/" target="_blank" class="facebook"></a>
                                </li>
                                <li>
                                    <a href="http://techcomsoft.vn/go/twitter/" target="_blank" class="twitter"></a>
                                </li>
                                <li>
                                    <a href="http://www.techcomsoft.vn/go/googleplus/" target="_blank" class="linkin"></a>
                                </li>
                            </ul>
                        </div>
                        <ul class="navigation">
                            <li>
                                <h5>Mens</h5>
                                <a href="mens.html">Sneakers</a> <a href="mens.html">Boots</a> <a href="mens.html">Winter socks</a> <a href="mens.html">Lace-ups</a>
                            </li>
                            <li>
                                <h5>Womens</h5>
                                <a href="womens.html">Sneakers</a> <a href="womens.html">Boots</a> <a href="womens.html">Winter socks</a> <a href="womens.html">Lace-ups</a>
                            </li>
                            <li class="latest">
                                <h5>New Arrivals</h5>
                                <a href="new.html">Cheverlyn Zespax</a> <a href="new.html">Alta Ulterior</a> <a href="new.html">Mikee</a> <a href="new.html">Jeeroks Copy</a>
                            </li>
                            <li class="latest">
                                <h5>On Sale Items</h5>
                                <a href="sale.html">Cheverlyn Zespax</a> <a href="sale.html">Alta Ulterior</a> <a href="sale.html">Mikee</a> <a href="sale.html">Jeeroks Copy</a>
                            </li>
                        </ul>
                        <p class="footnote">
                            &copy; Copyirght &copy; 2011. <a href="register.html">Company name</a> all rights reserved.
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
