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
    <div class="d-flex justify-content-around" style="margin: 5px;">
        <button type="button" class="btn btn-outline-info"
                onclick="window.location.href='controller?command=home_page'">
            ${home}
        </button>
        <button type="button" class="btn btn-outline-info"
                onclick="window.location.href='controller?command=edit_profile'">
            ${edit}
        </button>
        <button type="button" class="btn btn-outline-info"
                onclick="window.location.href='controller?command=register_pet'">
            ${registerpet}
        </button>
    </div>
    <div class="d-flex justify-content-center m-5">
        <h4>${userinfo}</h4>
    </div>
    <div style="margin-left: 20px; margin-right: 20px">
        <div class="row rounded bg-light border-dark p-3" style="margin-bottom: inherit">
            <div class="col-6">
                <p>${userlogin}</p>
            </div>
            <div class="col-6">
                <p>${user.login}</p>
            </div>
        </div>
        <div class="row rounded bg-light border-dark p-3" style="margin-bottom: inherit">
            <div class="col-6">
                <p>${username}</p>
            </div>
            <div class="col-6">
                <p>${user.name}</p>
            </div>
        </div>
        <div class="row rounded bg-light border-dark p-3" style="margin-bottom: inherit">
            <div class="col-6">
                <p>${useremail}</p>
            </div>
            <div class="col-6">
                <p><a href="mailto:${user.email}">${user.email}</a></p>
            </div>
        </div>
        <div class="row rounded bg-light border-dark p-3" style="margin-bottom: inherit">
            <div class="col-6">
                <p>${userphone}</p>
            </div>
            <div class="col-6">
                <p><a href="callto:${user.phoneNumber}">${user.phoneNumber}</a></p>
            </div>
        </div>
        <div class="row rounded bg-light border-dark p-3" style="margin-bottom: inherit">
            <div class="col-6">
                <p>${useraddress}</p>
            </div>
            <div class="col-6">
                <p>${user.address}</p>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-center m-5">
        <p>${yourpets}</p>
    </div>
    <div class=" rounded bg-light border-dark p-3" style="margin-bottom: inherit">
        <c:forEach var="pet" items="${pets}">
            <div class="row">
                <div class="col-3">
                    <label>${pet.name}</label>
                </div>
                <div class="col-3">
                    <label><fmt:formatDate value="${pet.dateOfBirth}" pattern="yyyy-MM-dd HH:mm"/></label>
                </div>
                <div class="col-3">
                    <p>${pet.kind}</p>
                </div>
                <div class="col-3" style="text-align: left">
                    <p><a href="controller?command=delete_pet&name=${pet.name}">${deletepet}</a></p>
                    <p><a href="controller?command=take_coupon&pet_id=${pet.identity}">${take}</a></p>
                </div>
            </div>
        </c:forEach>
        <c:if test="${error_delete eq true}">
            <div class="m-16 alert alert-warning " role="alert">
                    ${errordelete}
            </div>
        </c:if>
    </div>

    <c:if test="${coupons != null}">
        <div class="d-flex justify-content-center m-5">
            <p>${cs}</p>
        </div>
        <div class=" rounded bg-light border-dark p-3" style="margin-bottom: inherit">
            <c:forEach var="coupon" items="${coupons}">
                <div class="row">
                    <div class="col-3">
                        <c:forEach var="doctor" items="${doctors}">
                            <c:if test="${doctor.identity eq coupon.doctor_id}">
                                <label>${doctor.name}</label>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="col-3">
                        <label><fmt:formatDate value="${coupon.time}"
                                               pattern="yyyy-MM-dd HH:mm"/></label>
                    </div>
                    <div class="col-3">
                        <c:forEach var="service" items="${services}">
                            <c:if test="${service.identity eq coupon.service_id}">
                                <label>${service.name}</label>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="col-3">
                        <p>
                            <a href="">update</a>
                        </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

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

    .profile-img img {
        width: 70%;
        height: 100%;
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

    .proile-rating span {
        color: #495057;
        font-size: 15px;
        font-weight: 600;
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
