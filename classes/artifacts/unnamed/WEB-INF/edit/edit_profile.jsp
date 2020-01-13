<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<fmt:message bundle="${language}" key="editprofile" var="editprofile"/>
<fmt:message bundle="${language}" key="edit" var="edit"/>
<fmt:message bundle="${language}" key="enteryourphone" var="enterphone"/>
<fmt:message bundle="${language}" key="enteryouremail" var="enteremail"/>
<fmt:message bundle="${language}" key="enteryourname" var="entername"/>
<fmt:message bundle="${language}" key="enteroulogin" var="enterlogin"/>
<fmt:message bundle="${language}" key="enteryoupassword" var="enterpassword"/>
<fmt:message bundle="${language}" key="useaddress" var="useraddress"/>
<fmt:message bundle="${language}" key="profile" var="profile"/>
<fmt:message bundle="${language}" key="login" var="log"/>
<fmt:message bundle="${language}" key="username" var="username"/>
<fmt:message bundle="${language}" key="useremail" var="useremail"/>
<fmt:message bundle="${language}" key="userphone" var="userphone"/>
<fmt:message bundle="${language}" key="userpassword" var="userpassword"/>
<fmt:message bundle="${language}" key="erroremail" var="erroremail"/>
<fmt:message bundle="${language}" key="errorlogin" var="errorlogin"/>
<fmt:message bundle="${language}" key="wrongaddress" var="wrongaddress"/>
<fmt:message bundle="${language}" key="wrongemail" var="wrongemail"/>
<fmt:message bundle="${language}" key="wronglogin" var="wronglogin"/>
<fmt:message bundle="${language}" key="wrongname" var="wrongname"/>
<fmt:message bundle="${language}" key="wrongpassword" var="wrongpassword"/>
<fmt:message bundle="${language}" key="wrongphone" var="wrongphone"/>
<fmt:message bundle="${language}" key="phoneproperties" var="phoneprop"/>



<html lang="${language}">
<head>
    <title>${editprofile}</title>
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
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="main-form">
            <form class="login10-form" method="POST"
                  action="controller?command=edit_profile">
					<span class="login100-form-title">
						${editprofile}
					</span>

                <div class="form-group">
                    <label for="login" class="cols-sm-2 control-label">${log}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="login" id="login"
                                   placeholder="${user.login}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">${userpassword}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="password" id="password"
                                   placeholder="${enterpassword}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">${username}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="name" id="name"
                                   placeholder="${user.name}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">${useremail}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="email" id="email"
                                   placeholder="${user.email}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phoneNumber" class="cols-sm-2 control-label">${userphone}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-users fa"
                                                                       aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber"
                                   placeholder="${user.phoneNumber}"/>
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label for="address" class="cols-sm-2 control-label">${useraddress}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="address" id="Address"
                                   placeholder="${user.address}"/>
                        </div>
                    </div>
                </div>

                <c:if test="${error_registration eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                            ${errorlogin}
                    </div>
                </c:if>

                <c:if test="${error_email eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                            ${erroremail}
                    </div>
                </c:if>

                <c:if test="${email_error eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                        ${wrongemail}
                    </div>
                </c:if>

                <c:if test="${login_error eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                        ${wronglogin}
                    </div>
                </c:if>

                <c:if test="${error_number eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                        ${phoneprop}
                    </div>
                </c:if>

                <c:if test="${password_error eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                        ${wrongpassword}
                    </div>
                </c:if>

                <c:if test="${address_error eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                        ${wrongaddress}
                    </div>
                </c:if>


                <c:if test="${phone_error eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                        ${wrongphone}
                    </div>
                </c:if>


                <c:if test="${name_error eq true}">
                    <div class="container alert alert-warning alert-dismissible fade show m-t-16" role="alert">
                        ${wrongname}
                    </div>
                </c:if>

                <div class="container-login100-form-btn">
                    <button type="submit" class="login100-form-btn">
                        ${edit}
                    </button>
                </div>

                <div class="container-login100-form-btn">
                    <a href="controller?command=profile" class="profile-edit-btn">${profile}</a>
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

