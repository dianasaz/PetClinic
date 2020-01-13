<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 04.08.2019
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>


<fmt:message bundle="${language}" key="editprofile" var="edit"/>
<fmt:message bundle="${language}" key="errordelete" var="errordelete"/>
<fmt:message bundle="${language}" key="home" var="home"/>
<fmt:message bundle="${language}" key="addnewdoctor" var="add"/>
<fmt:message bundle="${language}" key="deletedoctor" var="deletedoctor"/>
<fmt:message bundle="${language}" key="editdoctor" var="editdoctor"/>
<fmt:message bundle="${language}" key="doctorscaps" var="doctorcaps"/>


<html lang="${language}">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<div class="container emp-profile">
    <div style="margin-left: 20%; margin-right: 20%;">
        <table style="margin-bottom: 20px">
            <div class="row">
                <div class="col-md-6">
                    <a href="controller?command=home_page" class="profile-edit-btn">${home}</a>
                </div>
                <c:if test="${user != null}">
                    <c:if test="${user_role eq 'administrator'}">
                        <div class="col-md-6">
                            <a href="controller?command=add_doctor">${add}</a>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </table>
    </div>
    <form class="form" method="POST" action="controller?command=watch_doctor">
        <div class="row">
            <table class="col-md-8">
                <div class="tab-content" id="myTaabContent">
                    <label class="col-md-6 col-md-offset-3 control-label" style="font-size: 20px; text-align: center;">
                        <b>${doctorcaps}</b> </label>

                    <c:forEach var="doctor" items="${doctors}">
                        <div class="row">
                            <div class="col-md-3">
                                <label>${doctor.name}</label>
                            </div>
                            <div class="col-md-3">
                                <c:forEach var="service" items="${doctor.service}">
                                    <p>${service.name}</p>
                                </c:forEach>
                            </div>
                            <c:if test="${user != null}">
                                <c:if test="${user_role eq 'administrator'}">
                                    <div class="col-md-3">
                                        <a href="controller?command=delete_doctor&name=${doctor.name}">${deletedoctor}</a>
                                    </div>
                                    <div class="col-md-3">
                                        <a href="controller?command=edit_doctor&doctor_id=${doctor.identity}">${editdoctor}</a>
                                    </div>
                                </c:if>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </table>
        </div>
    </form>

</div>
<style>
    body {
        background: -webkit-linear-gradient(left, #fffceb, #00c6ff);
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
