<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${sessionScope.lang != null}">
        <fmt:setLocale value="${sessionScope.lang}" variant="en"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="ru"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="language" scope="session"/>

<fmt:message bundle="${language}" key="editprofile" var="edit"/>
<fmt:message bundle="${language}" key="errordelete" var="errordelete"/>
<fmt:message bundle="${language}" key="home" var="home"/>
<fmt:message bundle="${language}" key="registerpet" var="registerpet"/>
<fmt:message bundle="${language}" key="userinfo" var="userinfo"/>
<fmt:message bundle="${language}" key="userlogin" var="userlogin"/>
<fmt:message bundle="${language}" key="username" var="username"/>
<fmt:message bundle="${language}" key="useremail" var="useremail"/>
<fmt:message bundle="${language}" key="userphone" var="userphone"/>
<fmt:message bundle="${language}" key="useaddress" var="useraddress"/>
<fmt:message bundle="${language}" key="yourpets" var="yourpets"/>
<fmt:message bundle="${language}" key="deletepet" var="deletepet"/>
<fmt:message bundle="${language}" key="takecoupon" var="take"/>
<fmt:message bundle="${language}" key="yourcoupons" var="cs"/>
<fmt:message bundle="${language}" key="errorNullSearch" var="searchNull"/>


<html lang="${language}">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container emp-profile">
    <div class="d-flex justify-content-start">
        <button type="button" class="btn btn-outline-info p-l-10"
                onclick="window.location.href='controller?command=home_page'">${home}</button>
    </div>
    <br>
    <c:choose>
        <c:when test="${error_null_search eq true}">
            <div class="m-16 alert alert-info " role="alert">
               ${searchNull}
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="object" items="${objects}">
                <div class="rounded bg-light border-dark p-3" style="margin-bottom: inherit">
                    <div class="row">
                        <div class="col-6">
                            <p>${object.name}</p>
                        </div>
                        <div class="col-6">
                            <p>${object.getClass().getSimpleName()}</p>
                        </div>
                    </div>
                </div>
                <!--<div class="row">
                    <div class="col-6">
                        <label>${object.name}</label>
                    </div>
                    <div class="col-6">
                        <p>${object.getClassName()}</p>
                    </div>
                </div>
                -->
            </c:forEach>
        </c:otherwise>
    </c:choose>


    <!--<div style="margin-left: 20%; margin-right: 20%;">
        <table style="margin-bottom: 20px">
            <div class="row">
                <div class="col-4">
                    <button type="button" class="btn btn-outline-info"
                            onclick="window.location.href='controller?command=home_page'">${home}</button>
                </div>
            </div>
        </table>
    </div>
    <div class="row">
        <table class="col-8">
            <div class="tab-content" id="myTaabContent">
                <label class="col-6 col-offset-3 control-label" style="font-size: 20px; text-align: center;">
                    <b>Result</b> </label>
                <c:choose>
                    <c:when test="${error_null_search eq true}">
                        <div class="row">
                            <div class="col-12">
                                <label>${searchNull}</label>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="object" items="${objects}">
                            <div class="row">
                                <div class="col-6">
                                    <label>${object.name}</label>
                                </div>
                                <div class="col-6">
                                    <p>${object.getClassName()}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </table>
    </div>
    -->
</div>
<style>
    body {
        background: -webkit-linear-gradient(left, #f5f5f5, #9cd6f3c4);
    }

    .emp-profile {
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 0.5rem;
        background: #fff;
    }

    .tab-content {
        margin-left: 20%;
        margin-right: 20%;
        width: 500px;
    }

    .profile-img img {
        width: 70%;
        height: 100%;
    }

    .profile-img .file {
        position: relative;
        overflow: hidden;
        margin-top: -20%;
        width: 70%;
        border: none;
        border-radius: 0;
        font-size: 15px;
    }

    .profile-img .file input {
        position: absolute;
        opacity: 0;
        right: 0;
        top: 0;
    }

    .profile-head h5 {
        color: #333;
    }

    .profile-head h6 {
        color: #0062cc;
    }

    .profile-edit-btn {
        border: none;
        border-radius: 1.5rem;
        width: 70%;
        padding: 2%;
        font-weight: 600;
        color: #6c757d;
        cursor: pointer;
    }

    .proile-rating span {
        color: #495057;
        font-size: 15px;
        font-weight: 600;
    }

    .profile-head .nav-tabs {
        margin-bottom: 5%;
    }

    .profile-head .nav-tabs .nav-link {
        font-weight: 600;
        border: none;
    }

    .profile-head .nav-tabs .nav-link.active {
        border: none;
        border-bottom: 2px solid #0062cc;
    }

    .profile-work p {
        font-size: 12px;
        color: #818182;
        font-weight: 600;
        margin-top: 10%;
    }

    .profile-work a {
        text-decoration: none;
        color: #495057;
        font-weight: 600;
        font-size: 14px;
    }

    .profile-work ul {
        list-style: none;
    }

    .profile-tab label {
        font-weight: 600;
    }

    .profile-tab p {
        font-weight: 600;
    }
</style>
</body>
</html>