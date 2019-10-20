<%-- 
    Document   : search
    Created on : Oct 27, 2018, 11:51:25 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- Website template by techcomsoft.vn -->
<html>

    <!-- Mirrored from www.techcomsoft.vn/websites/124/new.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 27 Oct 2018 14:05:37 GMT -->
    <head>
        <meta charset="UTF-8">
        <title>Sneaker Shop</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <style>
            .pagination {
                margin-left: 300px;
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }

            .pagination a:hover:not(.active) {background-color: #ddd;}
        </style>
        <script src="G:\\ProjectJava\\SneakerShop\\web\\WEB-INF\\js\\request.js"></script>
    </head>
    <body>
        <div id="background">
            <div id="page">
                <jsp:include page="page/header.jsp"/>
                <div id="contents">

                    <div class="footer">
                        <h4><span>Standard Shoes</span>
                            <select onchange="sort(this)" style="margin-left:700px;">
                                <option value="0">Sắp xếp</option>
                                <option value="DESC">Từ giá cao đến giá thấp</option>
                                <option value="ASC">Từ giá thấp đến giá cao</option>
                            </select>
                            <script>
                                function sort(option)
                                {
                                    if (option.value !== "0")
                                    {
                                        localStorage.sort = option.value;
                                        moveFirst(1);
                                    } else
                                    {
                                        localStorage.removeItem('sort');
                                        moveFirst(1);
                                    }
                                }
                            </script>
                        </h4>

                        <ul class="items" id="product_search">
                            <c:set var="result" value="${requestScope.LIST}" />
                            <c:if test="${not empty result}">
                                <c:forEach var="dto" items="${result}" varStatus="counter" >
                                    <c:if test="${counter.count <= 10}">
                                        <li>
                                            <a href=MainController?action=detail&id=${dto.id}> <img class="default-size" src="${dto.picture}" alt="Img">

                                                <span class="vnd">${dto.price}</span> 
                                                <br>
                                                ${dto.name} </a>
                                        </li>
                                    </c:if>         
                                </c:forEach>
                            </c:if>
                        </ul>

                        <script>
                            function formatVietnameseCurrency(price)
                            {
                                return new Intl.NumberFormat('vn-VN', {style: 'currency', currency: 'VND'}).format(price)
                            }
                            function VNDCurrency()
                            {
                                var price = document.getElementsByClassName('vnd');
                                // console.log(price);
                                for (var i = 0; i < price.length; i++) {
                                    price[i].innerText = formatVietnameseCurrency(price[i].innerText);
                                }

                            }
                            VNDCurrency();
                        </script>

                    </div>
                </div>
                <div class="pagination">



                </div>    
                <script>

                    var page = document.getElementsByClassName('pagination')[0];
                    page.innerHTML += '<a href="#" onclick="moveFirst(1)">&laquo;</a>';
                    var lastValue = 0;
                    if (localStorage.beginPage !== undefined)
                    {
                        var getPage = JSON.parse(localStorage.beginPage);
                        console.log("a" + getPage);
                        var beginPage = getPage;
                        localStorage.beginPage = JSON.stringify(getPage);
                        var endPage = getPage + 5;
                        page.innerHTML = '<a href="#" onclick="moveFirst(1)">&laquo;</a>';
                        if (${sessionScope.PAGE} > endPage)
                        {
                            for (var i = beginPage; i <= endPage; i++) {
                                page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                                lastValue = i;
                            }
                            page.innerHTML += '<a href="#" onclick="loadMore(' + lastValue + ')">...</a>'
                            page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                        } else
                        {
                            for (var i = beginPage; i <= ${sessionScope.PAGE}; i++) {
                                page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                            }
                            page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                        }
                    } else
                    {

                        if (${sessionScope.PAGE} > 5)
                        {
                            page.innerHTML += '<a href="MainController?action=showshop&shopUrl=${sessionScope.SHOPNAME}&page=1" class="active">1</a>';
                            for (var i = 2; i <= 5; i++) {
                                page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                                lastValue = i;
                            }
                            page.innerHTML += '<a href="#" onclick="loadMore(' + lastValue + ')">...</a>'
                            page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                        } else
                        {
                            page.innerHTML += '<a href="MainController?action=showshop&shopUrl=${sessionScope.SHOPNAME}&page=1" class="active">1</a>';
                            for (var i = 2; i <= ${sessionScope.PAGE}; i++) {
                                page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                            }
                            page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                        }
                    }
                </script>
                <script>
                    function loadMore(number)
                    {
                        var beginPage = number + 1;
                        localStorage.beginPage = JSON.stringify(beginPage);
                        var endPage = number + 5;
                        page.innerHTML = '<a href="#" onclick="moveFirst(1)">&laquo;</a>';

                        if (${sessionScope.PAGE} > endPage)
                        {
                            for (var i = beginPage; i <= endPage; i++) {
                                page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                                lastValue = i;
                            }
                            page.innerHTML += '<a href="#" onclick="loadMore(' + lastValue + ')">...</a>'
                            page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                        } else
                        {
                            for (var i = beginPage; i <= ${sessionScope.PAGE}; i++) {
                                page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                            }
                            page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                        }
                        console.log(localStorage.beginPage);

                    }
                    function movePage(pageNumber)
                    {
                        //var searchValue = "${requestScope.SEARCH}";
                        //var index = pageNumber;
                        //console.log(pageNumber);
                        //console.log(searchValue);
                        console.log(pageNumber);

                        var page = document.getElementsByClassName('pagination')[0];
                        page.innerHTML = "";
                        page.innerHTML = '<a href="#" onclick="moveFirst(1)">&laquo;</a>';
                        if (localStorage.beginPage !== undefined)
                        {
                            var getPage = JSON.parse(localStorage.beginPage);
                            var beginPage = getPage;
                            localStorage.beginPage = JSON.stringify(getPage);
                            var endPage = getPage + 5;
                            if (${sessionScope.PAGE} > endPage)
                            {
                                for (var i = beginPage; i <= endPage; i++) {
                                    page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                                    lastValue = i;
                                }
                                page.innerHTML += '<a href="#" onclick="loadMore(' + lastValue + ')">...</a>'
                                page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                            } else
                            {
                                for (var i = beginPage; i <= ${sessionScope.PAGE}; i++) {
                                    page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                                }
                                page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                            }
                        } else
                        {
                            if (${sessionScope.PAGE > 5})
                            {
                                for (var i = 1; i <= 5; i++) {
                                    if (pageNumber === i)
                                    {
                                        page.innerHTML += '<a href="#" class="active" onclick="movePage(' + i + ')">' + i + '</a>'

                                    } else
                                    {
                                        page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                                    }
                                }
                                page.innerHTML += '<a href="#" onclick="loadMore(' + lastValue + ')">...</a>'
                                page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                            } else
                            {
                                for (var i = 1; i <= ${sessionScope.PAGE}; i++) {
                                    if (pageNumber === i)
                                    {
                                        page.innerHTML += '<a href="#" class="active" onclick="movePage(' + i + ')">' + i + '</a>'

                                    } else
                                    {
                                        page.innerHTML += '<a href="#" onclick="movePage(' + i + ')">' + i + '</a>'
                                    }
                                }
                                page.innerHTML += '<a href="#" onclick="moveLast(' +${sessionScope.PAGE} + ')">&raquo;</a>'
                            }

                        }

                        if (pageNumber >= 1)
                        {
                            var sortBy = localStorage.sort;
                            console.log(sortBy);
                            if (sortBy !== undefined)
                            {
                                requestList({
                                    task: 'moveToPageNumberBySorting',
                                    shopUrl: '${sessionScope.SHOPNAME}',
                                    page: pageNumber,
                                    sort: sortBy
                                }, function (res) {
                                    console.log(res);
                                    var container = document.getElementById('product_search');
                                    var keywords = res.responseXML.getElementsByTagName('product');
                                    console.log(keyword);
                                    container.innerHTML = "";
                                    for (var i = 0; i < keywords.length && i < 10; i++) {
                                        var keyword = keywords[i];
                                        var id = keyword.childNodes[0].firstChild.nodeValue;
                                        var name = keyword.childNodes[1].firstChild.nodeValue;
                                        var price = keyword.childNodes[2].firstChild.nodeValue;
                                        var picture = keyword.childNodes[3].firstChild.nodeValue;
                                        price = formatVietnameseCurrency(price);
                                        container.innerHTML += '<li><a href="MainController?action=detail&id=' + id + '">' + '<img class="default-size" src="' + picture + '"alt="Img"> </br> <span>' + price + '</span></br>' + name + '</a></li>';
                                    }
                                });
                            } else
                            {
                                requestList({
                                    task: 'moveToPageNumber',
                                    shopUrl: '${sessionScope.SHOPNAME}',
                                    page: pageNumber
                                }, function (res) {
                                    console.log(res);
                                    var container = document.getElementById('product_search');
                                    var keywords = res.responseXML.getElementsByTagName('product');
                                    console.log(keyword);
                                    container.innerHTML = "";
                                    for (var i = 0; i < keywords.length && i < 10; i++) {
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

                        }
                    }
                    function moveFirst(number)
                    {
                        localStorage.beginPage = number;
                        movePage(number);
                    }
                    function moveLast(number)
                    {
                        localStorage.beginPage = number - 6;
                        movePage(number);
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

    <!-- Mirrored from www.techcomsoft.vn/websites/124/new.html by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 27 Oct 2018 14:05:38 GMT -->
</html>
