<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>


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
                <div class="col-md-4">
                    <a href="controller?command=edit_profile" class="profile-edit-btn">${edit}</a>
                </div>
                <div class="col-md-4">
                    <a href="controller?command=home_page" class="profile-edit-btn">${home}</a>
                </div>
                <div class="col-md-4">
                    <a href="controller?command=register_pet" class="profile-edit-btn">${registerpet}</a>
                </div>
            </div>
        </table>
    </div>
    <form class="form" method="POST" action="controller?command=profile">
        <div class="row">
            <table class="col-md-8">

                <div class="tab-content" id="myTabContent">
                    <label class="col-md-6 col-md-offset-3 control-label" style="font-size: 20px; text-align: center;">
                        <b>${userinfo}</b> </label>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${userlogin}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${user.login}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${username}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${user.name}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${useremail}</label>
                        </div>
                        <div class="col-md-6">
                            <p><a href="mailto:${user.email}">${user.email}</a></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${userphone}</label>
                        </div>
                        <div class="col-md-6">
                            <p><a href="callto:${user.phoneNumber}">${user.phoneNumber}</a></p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>${useraddress}</label>
                        </div>
                        <div class="col-md-6">
                            <p>${user.address}</p>
                        </div>
                    </div>

                </div>
            </table>
            <table class="col-md-8">
                <div class="tab-content" id="myTaabContent">
                    <label class="col-md-6 col-md-offset-3 control-label" style="font-size: 20px; text-align: center;">
                        <b>${yourpets}</b> </label>

                    <c:forEach var="pet" items="${pets}">
                        <div class="row">
                            <div class="col-md-2">
                                <label>${pet.name}</label>
                            </div>
                            <div class="col-md-2">
                                <p>${pet.kind}</p>
                            </div>
                            <div class="col-md-2">
                                <p>${pet.dateOfBirth}</p>
                            </div>
                            <div class="col-md-2">
                                <p><a href="controller?command=delete_pet&name=${pet.name}">${deletepet}</a></p>
                            </div>
                            <div class="col-md-2">
                                <p><a href="controller?command=take_coupon&pet_id=${pet.identity}">${take}</a></p>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${error_delete eq true}">
                        <div class="container1" role="alert">
                                ${errordelete}
                        </div>
                    </c:if>
                </div>
            </table>
            <table class="col-md-8">
                <div class="tab-content" id="myTaaabContent">
                    <label class="col-md-6 col-md-offset-3 control-label" style="font-size: 20px; text-align: center;">
                        <b>${cs}</b> </label>

                    <c:if test="${coupons != null}">
                        <c:forEach var="coupon" items="${coupons}">
                            <div class="row">
                                <div class="col-md-4">
                                    <c:forEach var="doctor" items="${doctors}">
                                        <c:if test="${doctor.identity eq coupon.doctor_id}">
                                            <p>${doctor.name}</p>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="col-md-4">
                                    <label><fmt:formatDate value="${coupon.time}" pattern="yyyy-MM-dd HH:mm"/></label>
                                </div>
                                <div class="col-md-4">
                                    <c:forEach var="service" items="${services}">
                                        <c:if test="${service.identity eq coupon.service_id}">
                                            <p>${service.name}</p>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="col-md-4">
                                    <p><a href="controller?command=edit_coupon&coupon_id=${coupon.identity}">update</a></p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </table>
        </div>
    </form>

</div>
<style>
    body {
        background: -webkit-linear-gradient(left, #f2fff0, #00c6ff);
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