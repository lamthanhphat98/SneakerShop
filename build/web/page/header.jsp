<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <div id="logo">
        <a href="register.jsp"> <img src="images/LogoMakr_9xUPyW.png" alt="LOGO"/></a>

    </div>
    <div id="navigation">
        <ul id="primary">
            <li class="selected">
                <a href="index.jsp" onclick="clearStorage()">Home</a>
            </li>

            <li>
                <a href="MainController?action=showshop&shopUrl=Saigon&page=1" onclick="clearStorage()">Standard</a>
            </li>
            <li>
                <a href="MainController?action=showshop&shopUrl=KingShoes&page=1" onclick="clearStorage()">KingShoes</a>
            </li>
            <li>
                <a href="MainController?action=showshop&shopUrl=Centimet&page=1" onclick="clearStorage()">Centimet</a>
            </li>
            <script>
                function clearStorage()
                {
                    if (localStorage.beginPage !== undefined)
                    {
                        localStorage.removeItem('beginPage');
                    }
                    if (localStorage.sort !== undefined)
                    {
                        localStorage.removeItem('sort');
                    }
                }
            </script>
        </ul>
        <ul id="secondary">
            <c:if test="${sessionScope.USERNAME != null}">
                <li>
                    <a href="MainController?action=viewcart">Checkout</a>
                </li>
                <li>
                    <a href="profile">${sessionScope.FULLNAME}</a>
                </li>
                <li>
                    <a href="MainController?action=logout">Logout</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.USERNAME == null}">
                <li>
                    <a href="login.jsp">Login</a> |  <a href="loginadmin.jsp">DASH BOARD</a>| <a href="register.jsp">SignUp</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>