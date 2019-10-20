<%-- 
    Document   : checkout
    Created on : Oct 28, 2018, 9:31:40 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<!DOCTYPE html>
<!-- Website template by techcomsoft.vn -->
<html>

    <!-- Mirrored from www.techcomsoft.vn/websites/124/checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 27 Oct 2018 14:05:38 GMT -->
    <head>
        <meta charset="UTF-8">
        <title>Phat SneakerShop</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <style>
            #txtfield
            {
                width: 50px;
            }
            .plus .minus
            {
                text-decoration: none;

            }

        </style>
    </head>
    <body>
        <div id="background">
            <div id="page">
                <jsp:include page="page/header.jsp"/>
                <div id="contents">
                    <div id="checkout">
                        <h4><span>Checkout</span></h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Shoes</th>
                                    <th>Quantity</th>
                                    <th>Size</th>
                                    <th>Price</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                            <s:form theme="simple">                              
                                <c:set var="xml" value="${requestScope.XMLCART}"/>
                                <c:if test="${not empty xml}">
                                    <c:import var="xsl" url="WEB-INF/xsl/cart.xsl"/>
                                    <x:transform doc="${xml}" xslt="${xsl}" />
                                </c:if>
                                <c:if test="${empty xml}">
                                    <td>Không có đơn hàng nào cả.</td>
                                </c:if>


                            </s:form>
                            </tbody>
                        </table>  
                        <script>
                            function formatVietnameseCurrency(price)
                            {
                                return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                            }
                            function VNDCurrency()
                            {
                                var price = document.getElementsByClassName('product_price');
                                for (var i = 0, max = price.length; i < max; i++) {
                                    price[i].innerText = formatVietnameseCurrency(price[i].innerText);
                                    console.log(price.innerText);
                                }
                            }
                            VNDCurrency();
                        </script>
                        <c:if test="${not empty xml}">
                            <a href="CheckoutController" class="proceed-btn">Proceed to Checkout</a>
                        </c:if>
                        <c:if test="${empty xml}">
                            <a href="CheckoutController" style=" pointer-events: none;
                               cursor: default;" class="proceed-btn">Proceed to Checkout</a>
                        </c:if>
                    </div>
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

    <!-- Mirrored from www.techcomsoft.vn/websites/124/checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 27 Oct 2018 14:05:38 GMT -->
</html>
