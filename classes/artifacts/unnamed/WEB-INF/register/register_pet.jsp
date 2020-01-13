<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <title>${signup}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>
<body>

<div class="limiter">
    <div class="container-login100">

        <div class="main-form">
                    <form class="login10-form" method="POST"
                          action="controller?command=register_pet">
					<span class="login100-form-title">
						${signup}
					</span>

                        <div class="form-group">
                            <label for="name" class="cols-sm-2 control-label">${username}</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="name" id="name"
                                           placeholder="${username}"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="cols-sm-2 control-label">${pet}</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <input type="radio" name="kind" value="cat"> ${cat}
                                    <input type="radio" name="kind" VALUE="dog"> ${dog}
                                    <input type="radio" name="kind" value="hamster"> ${hamster}
                                    <input type="radio" name="kind" value="parrot"> ${parrot}
                                    <input type="radio" name="kind" value="turtle"> ${turtle}
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <input type="text" class="form-control" name="dateOfBirth" id="dateOfBirth"
                                   placeholder="${enterdate}">
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


                        <c:if test="${date_error eq true}">
                            <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                                    ${wrongdate}
                            </div>
                        </c:if>

                        <c:if test="${name_error eq true}">
                            <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                                    ${wrongname}
                            </div>
                        </c:if>


                        <div class="container-login100-form-btn">
                            <button type="submit" class="login100-form-btn">
                                ${register}
                            </button>
                        </div>


                    </form>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="../../vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="../../vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="../../vendor/bootstrap/js/popper.js"></script>
<script src="../../vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="../../vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="../../vendor/daterangepicker/moment.min.js"></script>
<script src="../../vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="../../vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="../../js/main.js"></script>

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
        background: #009edf;
        color: #FFF;
        text-shadow: none;
        box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
        height: auto;
    }

    span.input-group-addon i {
        color: #009edf;
        font-size: 17px;
    }

    .main-form {
        width: auto;
    }

    .login10-form {
        margin-left: 30%;
        margin-right: 30%;
    }
</style>
</body>

</html>



