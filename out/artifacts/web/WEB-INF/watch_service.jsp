<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 31.07.2019
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
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
<fmt:message bundle="${language}" key="addnewservice" var="add"/>
<fmt:message bundle="${language}" key="deleteservice" var="deleteservice"/>
<fmt:message bundle="${language}" key="edit" var="edit"/>
<fmt:message bundle="${language}" key="services" var="servicescaps"/>
<fmt:message bundle="${language}" key="service" var="serv"/>
<fmt:message bundle="${language}" key="price" var="price"/>
<fmt:message bundle="${language}" key="actions" var="act"/>


<html lang="${language}">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<jsp:include page="header.jsp"/>
<c:set value="${user != null and user_role eq 'administrator'}" var="rule"/>

<div class="container emp-profile">
    <div class="d-flex justify-content-around" style="margin: 5px;">
        <button type="button" class="btn btn-outline-info"
                onclick="window.location.href='controller?command=home_page'">
            ${home}
        </button>
        <c:if test="${rule eq true}">
            <button type="button" class="btn btn-outline-info"
                    onclick="window.location.href='controller?command=add_service'">
                    ${add}
            </button>
        </c:if>
    </div>
    <br>
    <div style="margin-left: 5rem; margin-right: 5rem;">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">${serv}</th>
                <th scope="col">${price}</th>
                <c:if test="${rule eq true}">
                    <th scope="col">${act}</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="service" items="${services}">
                <tr>
                    <td>${service.name}</td>
                    <td>${service.price}</td>
                    <c:if test="${rule eq true}">
                        <td><a href="controller?command=delete_service&name=${service.name}">${deleteservice}</a>
                            <br>
                            <a href="controller?command=edit_service&id=${service.identity}">${edit}</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
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
        background: #212529b8;
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
