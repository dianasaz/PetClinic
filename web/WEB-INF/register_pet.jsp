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

<fmt:message bundle="${language}" key="signup" var="signup"/>
<fmt:message bundle="${language}" key="pet" var="pet"/>
<fmt:message bundle="${language}" key="cat" var="cat"/>
<fmt:message bundle="${language}" key="dog" var="dog"/>
<fmt:message bundle="${language}" key="turtle" var="turtle"/>
<fmt:message bundle="${language}" key="parrot" var="parrot"/>
<fmt:message bundle="${language}" key="hamster" var="hamster"/>
<fmt:message bundle="${language}" key="enterdate" var="enterdate"/>
<fmt:message bundle="${language}" key="register" var="register"/>
<fmt:message bundle="${language}" key="wrongdateregisterpet" var="wrongdate"/>
<fmt:message bundle="${language}" key="wrongname" var="wrongname"/>

<head>
    <title>${register}</title>
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
              action="controller?command=register_pet">
            <div class="m-2">
                <span class="login100-form-title">
                    ${register}
                </span>
            </div>

            <div class="form-group">
                <div class="cols-sm-10">
                    <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                        <input type="text" oninput="checkPetName()" class="form-control" name="name" id="name"
                               placeholder="${entername}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="cols-sm-10">
                    <div class="input-group">
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" name="kind" class="form-check-input" value="cat">${cat}
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" name="kind" class="form-check-input" value="dog">${dog}
                            </label>
                        </div>
                        <div class="form-check-inline disabled">
                            <label class="form-check-label">
                                <input type="radio" name="kind" class="form-check-input" value="hamster">${hamster}
                            </label>
                        </div>
                        <div class="form-check-inline disabled">
                            <label class="form-check-label">
                                <input type="radio" name="kind" class="form-check-input" value="turtle">${turtle}
                            </label>
                        </div>
                        <div class="form-check-inline disabled">
                            <label class="form-check-label">
                                <input type="radio" name="kind" class="form-check-input" value="parrot">${parrot}
                            </label>
                        </div>
            </div>
                </div>
            </div>
            <div class="form-group">
                <div class="cols-sm-10">
                    <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="dateOfBirth" id="dateOfBirth"
                               placeholder="${enterdate}"/>
                        <script>
                            $(function() {
                                $('input[name="dateOfBirth"]').daterangepicker({
                                        singleDatePicker: true,
                                        showDropdowns: true,
                                        minYear: 1990,
                                        maxYear: parseInt(moment().format('YYYY'),10)
                                    }
                                );
                            });
                        </script>
                    </div>
                </div>
            </div>

            <c:if test="${name_error eq true}">
                <div class="m-16 alert alert-warning" role="alert">
                        ${wrongname}
                </div>
            </c:if>

            <c:if test="${date_error eq true}">
                <div class="m-16 alert alert-warning " role="alert">
                        ${wrongdate}
                </div>
            </c:if>

            <div class="container-login100-form-btn">
                <button type="submit" class="btn btn-info p-l-10">${register}</button>
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







