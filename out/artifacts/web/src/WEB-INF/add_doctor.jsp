<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 04.08.2019
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${sessionScope.lang != null}">
        <fmt:setLocale value="${sessionScope.lang}" variant="en"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="ru"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="language" var="language" scope="session"/>

<fmt:message bundle="${language}" key="add" var="add"/>
<fmt:message bundle="${language}" key="services" var="servicesall"/>
<fmt:message bundle="${language}" key="entername" var="entername"/>
<fmt:message bundle="${language}" key="nameError" var="nameError"/>
<fmt:message bundle="${language}" key="errorExist" var="errorExist"/>
<fmt:message bundle="${language}" key="addnewdoctor" var="addnewdoctor"/>
<fmt:message bundle="${language}" key="doctorname" var="docname"/>

<head>
    <title>${addnewdoctor}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="vendor/daterangepicker/moment.js"></script>
    <script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css"/>
    <script src="js/validation/validation.js"></script>

</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-login100">

    <div class="main-form">
        <form class="login10-form" method="POST"
              action="controller?command=add_doctor">
            <div class="m-2">
                <span class="login100-form-title">
                    ${addnewdoctor}
                </span>
            </div>

            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">${docname}</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                        <input type="text" oninput="checkDoctor()" class="form-control" name="name" id="name"
                               placeholder="${entername}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="cols-sm-2 control-label">${servicesall}</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <c:forEach var="service" items="${services}">
                            <input type="checkbox" class="pb-5" name="service" value="${service.name}"> ${service.name}
                            <br>
                        </c:forEach>
                    </div>

                </div>
            </div>

            <c:if test="${name_error eq true}">
                <div class="m-16 alert alert-warning" role="alert">
                        ${nameError}
                </div>
            </c:if>

            <c:if test="${error_exist eq true}">
                <div class="m-16 alert alert-warning " role="alert">
                        ${errorExist}
                </div>
            </c:if>

            <div class="container-login100-form-btn">
                <button type="submit" class="btn btn-info p-l-10">${add}</button>
            </div>
        </form>
    </div>
</div>

<!--===============================================================================================-->
<script src="../../../../classes/artifacts/unnamed/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="../../../../classes/artifacts/unnamed/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="../../../../classes/artifacts/unnamed/vendor/bootstrap/js/popper.js"></script>
<script src="../../../../classes/artifacts/unnamed/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="../../../../classes/artifacts/unnamed/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="../../../../classes/artifacts/unnamed/vendor/daterangepicker/moment.min.js"></script>
<script src="../../../../classes/artifacts/unnamed/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="../../../../classes/artifacts/unnamed/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="../../../../classes/artifacts/unnamed/js/main.js"></script>

<style>
    .form-group {
        margin-bottom: 15px;
    }

    label {
        margin-bottom: 15px;
    }

    .form-control {
        height: auto !important;
        padding: 8px 12px !important;
    }

    .input-group {
        box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
    }

    .login100-form-title {
        margin-left: 3%;
        font-size: 30px;
    }

    .form-group button {
        background-color: whitesmoke;
    }

    .container-login100 {
        margin-top: 30px;
        margin: 0 auto;
        padding: 10px 40px;
        background: -webkit-linear-gradient(left, #f5f5f5, #addaf1c4);
        text-shadow: none;
        box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
        height: auto;
    }

    span.input-group-addon i {
        color: #009edf;
        font-size: 17px;
    }

    .main-form {
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 0.5rem;
        background: #fff;
    }

    .login10-form {
        margin-left: 30%;
        margin-right: 30%;
    }
</style>
</body>





