<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 15.07.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${sessionScope.lang != null}">
        <fmt:setLocale value="${sessionScope.lang}" variant="en"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="ru"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="language" scope="session"/>

<fmt:message bundle="${language}" key="singin" var="signin"/>
<fmt:message bundle="${language}" key="signout" var="signout"/>
<fmt:message bundle="${language}" key="workhours" var="workh"/>


    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!--/bootstarp-css -->
    <!--css-->
    <link rel="stylesheet" href="css/style_new.css" type="text/css" media="all"/>
    <!--/css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Petcare Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);
    </script>
    <!--fonts-->
    <link href='http://fonts.googleapis.com/css?family=Arimo:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
    <!--/fonts-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <script src="js/responsiveslides.min.js"></script>
    <script src="js/validation/validation.js"></script>


<div class="header d-flex align-items-center">
    <div class="container">
        <div class="row d-flex justify-content-between align-items-center">
            <div>
                <img src="images/logo.png" alt="" onclick="window.location.href='controller?command=home_page'"/>
            </div>
            <div>
                <strong class="rounded d-inline-block mb-2 text-white bg-info pr-2 pl-2">${workh}</strong>
            </div>
            <div>
                <form class="form-inline my-2 my-lg-0" action="controller" method="get">
                    <input type="hidden" value="search" name="command">
                    <input class="form-control mr-sm-2" name="search" type="search" placeholder="Search"
                           aria-label="Search" id="search" oninput="checkSearch()">
                    <button type="submit" class="btn btn-info" id="button_search" disabled>Search</button>
                </form>
            </div>
            <div>
                <c:choose>
                    <c:when test="${sessionScope.lang != null}">
                        <button type="button" class="btn btn-info"
                                onclick="window.location.href='controller?command=change_language&lang=${sessionScope.lang}'">
                                ${sessionScope.nextLang}
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-info"
                                onclick="window.location.href='controller?command=change_language&lang=ru'">
                            EN
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div>
                <c:choose>
                    <c:when test="${user == null}">
                        <button type="button" class="btn btn-info"
                                onclick="window.location.href='controller?command=login'">
                                ${signin}
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-info"
                                onclick="window.location.href='controller?command=logout'">
                                ${signout}
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<style>
    .header {
        background: -webkit-linear-gradient(left, #f5f5f5, #b3def4c4);
        min-height: 180px;
    }
</style>
