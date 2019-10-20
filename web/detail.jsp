<%-- 
    Document   : product
    Created on : Oct 27, 2018, 11:13:32 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Website template by techcomsoft.vn -->
<html>

    <!-- Mirrored from www.techcomsoft.vn/websites/124/product.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 27 Oct 2018 14:05:38 GMT -->
    <head>
        <meta charset="UTF-8">
        <title>Phat SneakerShop</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <script src="G:\\ProjectJava\\SneakerShop\\web\\WEB-INF\\js\\request.js"></script>
    </head>
    <body>
        <div id="background">
            <div id="page">
                <jsp:include page="page/header.jsp"/>

                <div id="contents">
                    <div id="product">            
                        <div class="section">
                            <c:set var="dto" value="${requestScope.DTO}"/>
                            <input type="hidden" value="${dto.shoesId}" id="product_id"/>
                            <c:if test="${not empty dto}">
                                <div>
                                    <img id="product_image" style="width: 400px;height: 350px" src="${dto.picture}" alt="Img"/>
                                </div>

                            </c:if>
                        </div>
                        <div class="section">
                            <h2><span id="format_price">${dto.price}</span><p id="product_name">${dto.name}</p></h2>
                            <button class="btn-success btn" data-toggle="modal" data-target="#myModal">Nhấp vào để xem website của shop</button>
                            <br>
                            <br>
                            <p> Hoặc là đặt hàng qua trang web của chúng tôi </p>
                            <form autocomplete="off" method="post">
                                <select name="selectSize" id="shoes_size">
                                    <option value="36">36</option>
                                    <option value="37">37</option>
                                    <option value="38">38</option>
                                    <option value="39">39</option>
                                    <option value="40">40</option>
                                    <option value="41">41</option>
                                    <option value="42">42</option>
                                </select>
                            </form>
                            <button onclick="clickButton();" class="btn-cart">Add to cart</button>
                            <script type="text/javascript">
                                function clickButton()
                                {
                                    if ('${sessionScope.USERNAME}' === null || '${sessionScope.USERNAME}'.length === 0)
                                    {
                                        window.location.href = "login.jsp";
                                    } else
                                    {
                                        addToCart();
                                    }
                                    function addToCart()
                                    {
                                        var size = document.getElementById('shoes_size');
                                        //console.log("a");
                                        var sizeName = size.options[size.selectedIndex].value;
                                        requestAddToCart({
                                            task: 'addToCart',
                                            shoesId: '${dto.shoesId}',
                                            name: "${dto.name}", 
                                            price: '${dto.price}',
                                            image: "${dto.picture}",
                                            size: sizeName,
                                        }, function (res) {
                                            console.log(res);
                                            myFunction();                                       
                                        });
                                    }
                                    function myFunction() {
                                        var x = document.getElementById("snackbar");
                                        // Add the "show" class to DIV
                                        x.className = "show";
                                        // After 3 seconds, remove the show class from DIV
                                        setTimeout(function () {
                                            x.className = x.className.replace("show", "");
                                        }, 3000);
                                    }
                                }
                            </script>
                        </div>
                    </div>

                </div>
                <script>
                    function formatVietnameseCurrency(price)
                    {
                        return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                    }
                    function formatPrice()
                    {
                        var price = document.getElementById('format_price');
                        var getPrice = price.textContent;
                        console.log(getPrice);
                        getPrice = formatVietnameseCurrency(getPrice);
                        price.innerText = getPrice;
                    }
                    formatPrice();
                </script>
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
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Website của shop này.</h4>
                    </div>
                    <div class="modal-body">
                        <p>${dto.selledBy}</p> <a href="${dto.selledBy}" target="_blank">Đi tới shop này.</a>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng lại.</button>
                    </div>
                </div>

            </div>
        </div>
        <div id="snackbar">Đôi giày đã được thêm vào giỏ</div>
    </body>

    <!-- Mirrored from www.techcomsoft.vn/websites/124/product.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 27 Oct 2018 14:05:40 GMT -->
</html>
