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
    <title>Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/validation/validation.js"></script>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form method="POST" style="margin-top: 20px"
              action="controller?command=register">

            <input type="text" oninput="checkLogin()" id="login" class="fadeIn second"
                   name="login" placeholder="${log}">
            <input type="password" oninput="checkPassword()" id="password" class="fadeIn third"
                   name="password" placeholder="${userpassword}"
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
            <input type="text" oninput="checkName()" id="name" class="fadeIn third"
                   name="name" placeholder="${username}"
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
            <input type="email" oninput="checkEmail()" id="email" class="fadeIn third"
                   name="email" placeholder="${useremail}"
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
            <input type="text" oninput="checkPhone()" id="phoneNumber" class="fadeIn third"
                   name="phoneNumber" placeholder="${userphone}"
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
            <input type="text" oninput="checkAddress()" id="address" class="fadeIn third"
                   name="address" placeholder="${useraddress}"
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
            <button type="button" class="btn btn-info" style="margin-top: 10px"
                    onclick="this.form.submit()">
                ${signup}
            </button>

        <c:if test="${error_authentification eq true}">
            <div class="m-16 alert alert-warning " role="alert">
                    ${enterinvalid}
            </div>
        </c:if>
        <c:if test="${error_registration eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${errorlogin}
            </div>
        </c:if>

        <c:if test="${error_email eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${erroremail}
            </div>
        </c:if>

        <c:if test="${email_error eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${wrongemail}
            </div>
        </c:if>

        <c:if test="${login_error eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${wronglogin}
            </div>
        </c:if>

        <c:if test="${error_number eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${phoneprop}
            </div>
        </c:if>

        <c:if test="${password_error eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${wrongpassword}
            </div>
        </c:if>

        <c:if test="${address_error eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${wrongaddress}
            </div>
        </c:if>

        <c:if test="${phone_error eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${wrongphone}
            </div>
        </c:if>

        <c:if test="${name_error eq true}">
            <div class="m-16 alert alert-warning" role="alert">
                    ${wrongname}
            </div>
        </c:if>
    </form>
    <div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
                            ${haveaccount}
                        </span>
        <button type="button" class="btn btn-outline-info"
                onclick="window.location.href='controller?command=login'">
            ${signin}
        </button>
    </div>
    </div>
    </div>
<style>
    html {
        background-color: #56baed;
    }

    body {
        font-family: "Poppins", sans-serif;
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
        -webkit-box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
        box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
        text-align: center;
    }

    #formFooter {
        background-color: #f6f6f6;
        border-top: 1px solid #dce8f1;
        padding: 25px;
        text-align: center;
        -webkit-border-radius: 0 0 10px 10px;
        border-radius: 0 0 10px 10px;
    }

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

    /* Simple CSS3 Fade-in Animation */
    .underlineHover:after {
        display: block;
        left: 0;
        bottom: -10px;
        width: 0;
        height: 2px;
        background-color: #56baed;
        content: "";
        transition: width 0.2s;
    }

    .underlineHover:hover {
        color: #0d0d0d;
    }

    .underlineHover:hover:after {
        width: 100%;
    }


    /* OTHERS */

    *:focus {
        outline: none;
    }
</style>
</body>

</html>


