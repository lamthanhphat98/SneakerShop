
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<html>

    <head>
        <meta charset="UTF-8">
        <title>Phat SneakerShop</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <script src="G:\\ProjectJava\\SneakerShop\\web\\WEB-INF\\js\\request.js"></script>


    </head>
    <body>
        <div id="background">
            <div id="page">
                <jsp:include page="page/header.jsp"/>
                <div id="contents">
                    <div id="adbox">
                        <div id="search">
                            <h3>Quick Search</h3>
                            <p>
                                Hãy tìm đôi giày mà bạn thích nào.
                            </p>

                            <div style="width: 200px">
                                <form action="MainController" method="post">
                                    <input id="search_keyup" onkeyup="myKey();" type="text" required="true" name="txtSearch"/>
                                    <div id="suggest_box"></div>
                                    <input type="hidden" name="page" value="1"/>
                                    <input  type="submit" onclick="saveSearch();" value="Search" name="action"/>
                                </form>
                                <script>
//                                    function testLocalstorage() {
//                                        var testValue = ['sneaker', 'van', 'puma'];
//                                        localStorage.testValue = JSON.stringify(testValue);
//                                        var getValue = JSON.parse(localStorage.testValue);
//                                        console.log(getValue);
//                                        function test() {
//                                            request({
//                                                action: 'test',
//                                                task: 'testLocal',
//                                                list: getValue
//                                            }, function (res) {
//                                                console.log(res.responseText);
//                                            });
//                                        }
//                                        test();
//                                    }
                                    function myKey()
                                    {
                                        var btnSearch = document.getElementById("search_keyup");
                                        var searchValue = btnSearch.value;
                                        var suggestBox = document.getElementById("suggest_box");
                                        if (searchValue.length > 0)
                                        {
                                            function suggestSearch() {
                                                request({
                                                    action: 'suggest',
                                                    task: 'findSearch',
                                                    search: searchValue
                                                }, function (res) {

                                                    var object = res.responseText;
                                                    suggestBox.innerHTML = "";
                                                    console.log(res);
                                                    suggestBox.innerHTML += res.responseText;
                                                });
                                            }
                                            suggestSearch();
                                        } else
                                        {
                                            suggestBox.innerHTML = "";
                                        }
                                    }
                                    function saveSearch()
                                    {
                                        var btnSearch = document.getElementById("search_keyup");
                                        var searchValue = btnSearch.value;
                                        var listSearch = [];
                                        var duplicate = true;
                                        if (${sessionScope.USERNAME != null})
                                        {
                                            if (localStorage.searchValue === undefined)
                                            {
                                                listSearch.push(searchValue);
                                                localStorage.searchValue = JSON.stringify(listSearch);
                                                console.log(listSearch);
                                            } else
                                            {
                                                var getList = JSON.parse(localStorage.searchValue);
                                                console.log(getList);
                                                for (var i = 0; i < getList.length; i++) {
                                                    if (getList[i] !== searchValue)
                                                    {
                                                        duplicate = false;
                                                    }
                                                }
                                                if (duplicate === false)
                                                {
                                                    getList.push(searchValue);
                                                    localStorage.searchValue = JSON.stringify(getList);
                                                }
                                            }
                                        }
                                    }
                                </script>
                            </div>
                        </div>
                        <img src="images/DVCTSXL.png" height="354.2" width="618" alt="Promo">
                    </div>
                    <div id="main">
                        <div id="new">
                            <h4><span>New Arrivals 
                                    <select onchange="categoryArrivals(this)" style="margin-left:750px;">
                                        <option value="0">Chọn shop</option>
                                        <option value="saigonsneaker">SaigonSneaker</option>
                                        <option value="kingshoes">KingShoes</option>
                                        <option value="centimet">Centimet</option>
                                        <option value="hoang-phuc">HoangPhuc</option>
                                    </select>
                                    <script>
                                        function categoryArrivals(option)
                                        {
                                            if (option.value !== "0")
                                            {
                                                console.log(option.value);
                                                request({
                                                    action: 'loadNewArrivalByCategory',
                                                    task: 'loadNewArrivalByCategory',
                                                    category: option.value
                                                }, function (res) {
                                                    console.log(res);
                                                    var clearContentNew1 = document.getElementById("product_news1");
                                                    var clearContentNew2 = document.getElementById("product_news2");
                                                    clearContentNew1.innerHTML = "";
                                                    clearContentNew2.innerHTML = "";
                                                    var keywords = res.responseXML.getElementsByTagName('product');
                                                    for (var i = 0; i < 8; i++) {
                                                        var container = document.getElementById("product_news1");
                                                        if (i >= 4)
                                                        {
                                                            container = document.getElementById("product_news2");
                                                        }
                                                        var keyword = keywords[i];
                                                        var id = keyword.childNodes[0].firstChild.nodeValue;
                                                        var name = keyword.childNodes[1].firstChild.nodeValue;
                                                        var price = keyword.childNodes[2].firstChild.nodeValue;
                                                        var picture = keyword.childNodes[3].firstChild.nodeValue;
                                                        price = formatVietnameseCurrency(price);
                                                        container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                                    }
                                                }
                                                )
                                            }
                                        }




                                    </script>
                                </span>
                            </h4>

                            <script>

                                function formatVietnameseCurrency(price)
                                {
                                    return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                                }
                                function loadData() {
                                    request({
                                        action: 'loadNewArrival',
                                        task: 'loadNewArrivals'
                                    }, function (res) {
                                        var divSale = document.getElementById("new");
                                        for (var i = 0; i < 2; i++) {
                                            divSale.innerHTML += "<ul class='items' id='product_news" + (i + 1) + "'>";
                                            divSale.innerHTML += "</ul>";
                                        }
                                        var keywords = res.responseXML.getElementsByTagName('product');
                                        for (var i = 0; i < 8; i++) {
                                            var container = document.getElementById("product_news1");
                                            if (i >= 4)
                                            {
                                                container = document.getElementById("product_news2");
                                            }

                                            var keyword = keywords[i];
                                            var id = keyword.childNodes[0].firstChild.nodeValue;
                                            var name = keyword.childNodes[1].firstChild.nodeValue;
                                            var price = keyword.childNodes[2].firstChild.nodeValue;
                                            var picture = keyword.childNodes[3].firstChild.nodeValue;
                                            price = formatVietnameseCurrency(price);
                                            container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                        }
                                    }
                                    )
                                }
                                ;

                                loadData();
                                function VNDCurrency()
                                {
                                    var price = document.getElementsByClassName('format_price');
                                    for (var i = 0, max = price.length; i < max; i++) {
                                        price[i].innerText = formatVietnameseCurrency(price[i].innerText);
                                        console.log(price.innerText);
                                    }
                                }
                                VNDCurrency();

                            </script>

                        </div>

                        <div id="sale">
                            <h4><span>Sale Items
                                    <select onchange="categorySale(this)" style="margin-left:750px;">
                                        <option value="0">Chọn shop</option>
                                        <option value="saigonsneaker">SaigonSneaker</option>
                                        <option value="kingshoes">KingShoes</option>
                                        <option value="centimet">Centimet</option>
                                        <option value="hoang-phuc">HoangPhuc</option>
                                    </select>
                                </span>
                                <script>
                                    function categorySale(option)
                                    {
                                        if (option.value !== "0")
                                        {
                                            console.log(option.value);
                                            request({
                                                action: 'loadSaleByCategory',
                                                task: 'loadSaleByCategory',
                                                category: option.value
                                            }, function (res) {
                                                console.log(res);
                                                var clearContentNew1 = document.getElementById("product_sales1");
                                                var clearContentNew2 = document.getElementById("product_sales2");
                                                clearContentNew1.innerHTML = "";
                                                clearContentNew2.innerHTML = "";
                                                var keywords = res.responseXML.getElementsByTagName('product');
                                                for (var i = 0; i < 8; i++) {
                                                    var container = document.getElementById("product_sales1");
                                                    if (i >= 4)
                                                    {
                                                        container = document.getElementById("product_sales2");
                                                    }
                                                    var keyword = keywords[i];
                                                    var id = keyword.childNodes[0].firstChild.nodeValue;
                                                    var name = keyword.childNodes[1].firstChild.nodeValue;
                                                    var price = keyword.childNodes[2].firstChild.nodeValue;
                                                    var picture = keyword.childNodes[3].firstChild.nodeValue;
                                                    price = formatVietnameseCurrency(price);
                                                    container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                                }
                                            }
                                            )
                                        }
                                    }
                                </script>
                            </h4>

                        </div>
                        <script>
                            function formatVietnameseCurrency(price)
                            {
                                return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                            }
                            function loadData() {
                                if (${sessionScope.USERNAME != null})
                                {
                                    request({
                                        action: 'loadPage',
                                        task: 'loadTrackUser'
                                    }, function (res) {
                                        console.log(res);
                                        var divSale = document.getElementById("sale");
                                        for (var i = 0; i < 2; i++) {
                                            divSale.innerHTML += "<ul class='items' id='product_sales" + (i + 1) + "'>";
                                            divSale.innerHTML += "</ul>";
                                        }
                                        for (var i = 0; i < 8; i++) {
                                            var container = document.getElementById("product_sales1");
                                            if (i >= 4)
                                            {
                                                container = document.getElementById("product_sales2");
                                            }
                                            var keywords = res.responseXML.getElementsByTagName('product');
                                            var keyword = keywords[i];
                                            var id = keyword.childNodes[0].firstChild.nodeValue;
                                            var name = keyword.childNodes[1].firstChild.nodeValue;
                                            var price = keyword.childNodes[2].firstChild.nodeValue;
                                            var picture = keyword.childNodes[3].firstChild.nodeValue;
                                            price = formatVietnameseCurrency(price);
                                            container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                        }
                                    }
                                    );
                                } else
                                {
                                    request({
                                        action: 'loadPage',
                                        task: 'loadNewSale'
                                    }, function (res) {
                                        console.log(res);
                                        var divSale = document.getElementById("sale");
                                        for (var i = 0; i < 2; i++) {
                                            divSale.innerHTML += "<ul class='items' id='product_sales" + (i + 1) + "'>";
                                            divSale.innerHTML += "</ul>";
                                        }
                                        for (var i = 0; i < 8; i++) {
                                            var container = document.getElementById("product_sales1");
                                            if (i >= 4)
                                            {
                                                container = document.getElementById("product_sales2");
                                            }
                                            var keywords = res.responseXML.getElementsByTagName('product');
                                            var keyword = keywords[i];
                                            var id = keyword.childNodes[0].firstChild.nodeValue;
                                            var name = keyword.childNodes[1].firstChild.nodeValue;
                                            var price = keyword.childNodes[2].firstChild.nodeValue;
                                            var picture = keyword.childNodes[3].firstChild.nodeValue;
                                            price = formatVietnameseCurrency(price);
                                            container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                        }
                                    }
                                    );
                                }

                            }
                            ;

                            loadData();
                        </script>
                        <div id="trend">
                            <h4><span>Trending NOW</span></h4>

                        </div>
                        <script>
                            function formatVietnameseCurrency(price)
                            {
                                return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                            }
                            function loadTrending() {
                                request({
                                    action: 'loadTrending',
                                    task: 'loadTrending'
                                }, function (res) {
                                    var divSale = document.getElementById("trend");
                                    for (var i = 0; i < 2; i++) {
                                        divSale.innerHTML += "<ul class='items' id='product_trend" + (i + 1) + "'>";
                                        divSale.innerHTML += "</ul>";
                                    }
                                    for (var i = 0; i < 8; i++) {
                                        var container = document.getElementById("product_trend1");
                                        if (i >= 4)
                                        {
                                            container = document.getElementById("product_trend2");
                                        }
                                        var keywords = res.responseXML.getElementsByTagName('product');
                                        var keyword = keywords[i];
                                        var id = keyword.childNodes[0].firstChild.nodeValue;
                                        var name = keyword.childNodes[1].firstChild.nodeValue;
                                        var price = keyword.childNodes[2].firstChild.nodeValue;
                                        var picture = keyword.childNodes[3].firstChild.nodeValue;
                                        price = formatVietnameseCurrency(price);
                                        container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                    }
                                });
                            }
                            loadTrending();
                        </script>
                        <c:if test="${sessionScope.USERNAME != null}">
                            <div id="history">
                                <h4><span>You searched</span></h4>

                            </div>
                            <script>
                                function formatVietnameseCurrency(price)
                                {
                                    return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                                }
                                function loadHistory() {
                                    request({
                                        action: 'loadHistory',
                                        task: 'loadHistory'
                                    }, function (res) {
                                        var divSale = document.getElementById("history");
                                        for (var i = 0; i < 2; i++) {
                                            divSale.innerHTML += "<ul class='items' id='product_history" + (i + 1) + "'>";
                                            divSale.innerHTML += "</ul>";
                                        }
                                        for (var i = 0; i < 8; i++) {
                                            var container = document.getElementById("product_history1");
                                            if (i >= 4)
                                            {
                                                container = document.getElementById("product_history2");
                                            }
                                            var keywords = res.responseXML.getElementsByTagName('product');
                                            var keyword = keywords[i];
                                            var id = keyword.childNodes[0].firstChild.nodeValue;
                                            var name = keyword.childNodes[1].firstChild.nodeValue;
                                            var price = keyword.childNodes[2].firstChild.nodeValue;
                                            var picture = keyword.childNodes[3].firstChild.nodeValue;
                                            price = formatVietnameseCurrency(price);
                                            container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                        }
                                    });
                                }
                                loadHistory();
                            </script>
                        </c:if>
                        <c:if test="${sessionScope.USERNAME != null}">
                            <div id="recommend">
                                <h4><span>You may also like</span></h4>
                                <ul class="items" id="product_recommend">                                                

                                </ul>
                            </div>
                            <script>
                                function formatVietnameseCurrency(price)
                                {
                                    return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                                }
                                // var testValue = ['sneaker', 'van', 'puma', 'adidas', 'nike', 'gucci'];
                                //localStorage.searchValue = JSON.stringify(testValue);
                                if (${sessionScope.USERNAME != null})
                                {
                                    var getListSearch = JSON.parse(localStorage.searchValue);
                                    if (getListSearch.length >= 5)
                                    {
                                        console.log(getListSearch);
                                        function loadRecommend() {
                                            request({
                                                action: 'recommendShoes',
                                                task: 'loadRecommendShoes',
                                                list: getListSearch
                                            }, function (res) {
                                                console.log("recomemend : " + res.responseText);
                                                if (getListSearch.length > 6)
                                                {
                                                    getListSearch.shift();
                                                    localStorage.searchValue = JSON.stringify(getListSearch);
                                                }
                                                var container = document.getElementById('product_recommend');
                                                var keywords = res.responseXML.getElementsByTagName('product');
                                                console.log(keyword);
                                                for (var i = 0; i < keywords.length && i < 4; i++) {
                                                    var keyword = keywords[i];
                                                    var id = keyword.childNodes[0].firstChild.nodeValue;
                                                    var name = keyword.childNodes[1].firstChild.nodeValue;
                                                    var price = keyword.childNodes[2].firstChild.nodeValue;
                                                    var picture = keyword.childNodes[3].firstChild.nodeValue;
                                                    price = formatVietnameseCurrency(price);
                                                    container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                                }
                                            });
                                        }
                                        loadRecommend();
                                    }
                                }

                            </script>
                        </c:if>
                    </div>
                </div>
                <div id="snackbar">Đơn hàng đã được chuyển đến shop , hãy chờ liên hệ của họ.</div>
                <script>
                    if (${requestScope.COMPLETE != null})
                    {
                        function myFunction() {
                            var div = document.getElementById("snackbar");
                            // Add the "show" class to DIV
                            div.className = "show";
                            // After 3 seconds, remove the show class from DIV
                            setTimeout(function () {
                                div.className = div.className.replace("show", "");
                            }, 6000);
                        }
                        myFunction();
                    }
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
    </body>

    <!-- Mirrored from www.techcomsoft.vn/websites/124/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 27 Oct 2018 14:05:16 GMT -->
</html>
