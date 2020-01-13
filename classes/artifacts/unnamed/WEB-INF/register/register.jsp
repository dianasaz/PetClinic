<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 29.07.2019
  Time: 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>


<fmt:message bundle="${language}" key="singin" var="signin"/>
<fmt:message bundle="${language}" key="enteroulogin" var="enterlogin"/>
<fmt:message bundle="${language}" key="enteryoupassword" var="enterpassword"/>
<fmt:message bundle="${language}" key="enteryouremail" var="enteremail"/>
<fmt:message bundle="${language}" key="enteryourname" var="entername"/>
<fmt:message bundle="${language}" key="enteryourphone" var="enterphone"/>
<fmt:message bundle="${language}" key="login" var="log"/>
<fmt:message bundle="${language}" key="useaddress" var="useraddress"/>
<fmt:message bundle="${language}" key="username" var="username"/>
<fmt:message bundle="${language}" key="useremail" var="useremail"/>
<fmt:message bundle="${language}" key="userphone" var="userphone"/>
<fmt:message bundle="${language}" key="userpassword" var="userpassword"/>
<fmt:message bundle="${language}" key="errorlogin" var="errorlogin"/>
<fmt:message bundle="${language}" key="signup" var="signup"/>
<fmt:message bundle="${language}" key="haveaccount" var="haveaccount"/>
<fmt:message bundle="${language}" key="logintoseeprofile" var="pleaselogin"/>
<fmt:message bundle="${language}" key="enterinvalid" var="enterinvalid"/>
<fmt:message bundle="${language}" key="donthaveaccount" var="donthaveacc"/>
<fmt:message bundle="${language}" key="continueguest" var="continuegueast"/>
<fmt:message bundle="${language}" key="register" var="register"/>
<fmt:message bundle="${language}" key="erroremail" var="erroremail"/>
<fmt:message bundle="${language}" key="wrongaddress" var="wrongaddress"/>
<fmt:message bundle="${language}" key="wrongemail" var="wrongemail"/>
<fmt:message bundle="${language}" key="wronglogin" var="wronglogin"/>
<fmt:message bundle="${language}" key="wrongname" var="wrongname"/>
<fmt:message bundle="${language}" key="wrongpassword" var="wrongpassword"/>
<fmt:message bundle="${language}" key="wrongphone" var="wrongphone"/>
<fmt:message bundle="${language}" key="phoneproperties" var="phoneprop"/>


<html lang="${language}">
<head>
    <title>${register}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <form method="POST"
                  action="controller?command=register">
					<span class="fadeIn first">
                        ${register}
                    </span>


                <div class="form-group">
                    <label for="login" class="cols-sm-2 control-label">${log}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                            <input type="text" id="login" class="fadeIn second" name="login"
                                   placeholder="${enterlogin}">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">${username}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa"
                                                                       aria-hidden="true"></i></span>
                            <input type="text" class="fadeIn second" name="name" id="name"
                                   placeholder="${entername}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">${useremail}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="fadeIn second" name="email" id="email"
                                   placeholder="${enteremail}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phoneNumber" class="cols-sm-2 control-label">${userphone}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-users fa"
                                                                       aria-hidden="true"></i></span>
                            <input type="text" class="fadeIn second" name="phoneNumber" id="phoneNumber"
                                   placeholder="${enterphone}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">${userpassword}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" id="password" class="fadeIn third" name="password"
                                   placeholder="${enterpassword}"
                                   style="  background-color: #f6f6f6;
        border: none;
        color: #0d0d0d;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 5px;
        width: 85%;
        border: 2px solid #f6f6f6;
        -webkit-transition: all 0.5s ease-in-out;
        -moz-transition: all 0.5s ease-in-out;
        -ms-transition: all 0.5s ease-in-out;
        -o-transition: all 0.5s ease-in-out;
        transition: all 0.5s ease-in-out;
        -webkit-border-radius: 5px 5px 5px 5px;
        border-radius: 5px 5px 5px 5px;">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="address" class="cols-sm-2 control-label">${useraddress}</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="text" class="fadeIn second" name="address" id="Address"
                                   placeholder="${useraddress}"/>
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



                <div class="fadeIn fourth">
                    <input type="submit" class="fadeIn fourth" value="${signup}">
                </div>


                <div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
                            ${haveaccount}
                        </span>

                    <a href="controller?command=login" class="txt3">
                        ${signin}
                    </a>
                </div>

            </form>

        </div>
    </div>
</div>

<style>
    .form-group {
        margin-bottom: 15px;
    }

    label {
        margin-bottom: 15px;
    }

    .input-group {
        box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
    }

    .form-group button {
        background-color: whitesmoke;
    }

    span.input-group-addon i {
        color: #009edf;
        font-size: 17px;
    }

    .txt3 {
        color: whitesmoke;
    }

    html {
        background-color: #56baed;
    }

    body {
        font-family: "Poppins", sans-serif;
        height: 100vh;
    }

    a {
        color: #92badd;
        display: inline-block;
        text-decoration: none;
        font-weight: 400;
    }

    h2 {
        text-align: center;
        font-size: 16px;
        font-weight: 600;
        text-transform: uppercase;
        display: inline-block;
        margin: 40px 8px 10px 8px;
        color: #cccccc;
    }


    /* STRUCTURE */

    .wrapper {
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: center;
        width: 100%;
        min-height: 100%;
        padding: 20px;
    }

    #formContent {
        -webkit-border-radius: 10px 10px 10px 10px;
        border-radius: 10px 10px 10px 10px;
        background: #fff;
        padding: 30px;
        width: 90%;
        max-width: 450px;
        position: relative;
        padding: 0px;
        -webkit-box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
        box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
        text-align: center;
    }


    h2.inactive {
        color: #cccccc;
    }

    h2.active {
        color: #0d0d0d;
        border-bottom: 2px solid #5fbae9;
    }


    /* FORM TYPOGRAPHY*/

    input[type=button], input[type=submit], input[type=reset] {
        background-color: #56baed;
        border: none;
        color: white;
        padding: 15px 80px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        text-transform: uppercase;
        font-size: 13px;
        -webkit-box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
        box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
        -webkit-border-radius: 5px 5px 5px 5px;
        border-radius: 5px 5px 5px 5px;
        margin: 5px 20px 40px 20px;
        -webkit-transition: all 0.3s ease-in-out;
        -moz-transition: all 0.3s ease-in-out;
        -ms-transition: all 0.3s ease-in-out;
        -o-transition: all 0.3s ease-in-out;
        transition: all 0.3s ease-in-out;
    }

    input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover {
        background-color: #39ace7;
    }

    input[type=button]:active, input[type=submit]:active, input[type=reset]:active {
        -moz-transform: scale(0.95);
        -webkit-transform: scale(0.95);
        -o-transform: scale(0.95);
        -ms-transform: scale(0.95);
        transform: scale(0.95);
    }

    input[type=text] {
        background-color: #f6f6f6;
        border: none;
        color: #0d0d0d;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 5px;
        width: 85%;
        border: 2px solid #f6f6f6;
        -webkit-transition: all 0.5s ease-in-out;
        -moz-transition: all 0.5s ease-in-out;
        -ms-transition: all 0.5s ease-in-out;
        -o-transition: all 0.5s ease-in-out;
        transition: all 0.5s ease-in-out;
        -webkit-border-radius: 5px 5px 5px 5px;
        border-radius: 5px 5px 5px 5px;
    }

    input[type=password] {
        background-color: #f6f6f6;
        border: none;
        color: inherit;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 5px;
        width: 85%;
        border: 2px solid #f6f6f6;
        -webkit-transition: all 0.5s ease-in-out;
        -moz-transition: all 0.5s ease-in-out;
        -ms-transition: all 0.5s ease-in-out;
        -o-transition: all 0.5s ease-in-out;
        transition: all 0.5s ease-in-out;
        -webkit-border-radius: 5px 5px 5px 5px;
        border-radius: 5px 5px 5px 5px;
    }

    input[type=text]:focus {
        background-color: #fff;
        border-bottom: 2px solid #5fbae9;
    }

    input[type=text]::placeholder {
        color: #cccccc;
    }

    input[type=password]:focus {
        background-color: #fff;
        border-bottom: 2px solid #5fbae9;
    }

    input[type=password]::placeholder {
        color: #cccccc;
    }


    /* ANIMATIONS */

    /* Simple CSS3 Fade-in-down Animation */
    .fadeInDown {
        -webkit-animation-name: fadeInDown;
        animation-name: fadeInDown;
        -webkit-animation-duration: 1s;
        animation-duration: 1s;
        -webkit-animation-fill-mode: both;
        animation-fill-mode: both;
    }

    @-webkit-keyframes fadeInDown {
        0% {
            opacity: 0;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
        }
        100% {
            opacity: 1;
            -webkit-transform: none;
            transform: none;
        }
    }

    @keyframes fadeInDown {
        0% {
            opacity: 0;
            -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
        }
        100% {
            opacity: 1;
            -webkit-transform: none;
            transform: none;
        }
    }

    /* Simple CSS3 Fade-in Animation */
    @-webkit-keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @-moz-keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
        }
        to {
            opacity: 1;
        }
    }

    .fadeIn {
        opacity: 0;
        -webkit-animation: fadeIn ease-in 1;
        -moz-animation: fadeIn ease-in 1;
        animation: fadeIn ease-in 1;

        -webkit-animation-fill-mode: forwards;
        -moz-animation-fill-mode: forwards;
        animation-fill-mode: forwards;

        -webkit-animation-duration: 1s;
        -moz-animation-duration: 1s;
        animation-duration: 1s;
    }

    .fadeIn.first {
        -webkit-animation-delay: 0.4s;
        -moz-animation-delay: 0.4s;
        animation-delay: 0.4s;
    }

    .fadeIn.second {
        -webkit-animation-delay: 0.6s;
        -moz-animation-delay: 0.6s;
        animation-delay: 0.6s;
    }

    .fadeIn.third {
        -webkit-animation-delay: 0.8s;
        -moz-animation-delay: 0.8s;
        animation-delay: 0.8s;
    }

    .fadeIn.fourth {
        -webkit-animation-delay: 1s;
        -moz-animation-delay: 1s;
        animation-delay: 1s;
    }

    *:focus {
        outline: none;
    }
</style>
</body>

</html>


