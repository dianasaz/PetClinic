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

<fmt:message bundle="${language}" key="noaccesstopage" var="noaccess"/>
<fmt:message bundle="${language}" key="home" var="home"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>${noaccess}</title>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700&subset=latin,cyrillic'
          rel='stylesheet' type='text/css'>
    <style type="text/css">
        html, body {
            width: 90%;
            height: 90%;
            overflow: hidden;
            margin: 0px;
            padding: 0px;
            font-family: 'Open Sans', sans-serif;
            font-size: 16px
        }

        body {
            background: url('../../images/404.png') center no-repeat #fff
        }

        .content {
            width: 90%;
            text-align: center;
            position: absolute;
            bottom: 10%;
            left: 0px;
        }

        .content a {
            display: inline-block;
            text-decoration: none
        }

        .content a:hover {
            opacity: 0.7
        }

        .content a, .content a:hover {
            color: #000;
            font-size: 14px
        }

        @media only screen and (max-width: 360px), screen and (max-height: 600px) {
            .content {
                position: static;
            }

            .content a {
                display: block;
                width: 100%;
                height: 100%;
                position: absolute;
                top: 0px;
                left: 0px;
                font-size: 0px;
                opacity: 0;
            }

            body {
                background-size: cover
            }
        }
    </style>
</head>
<body>
<div class="content">
    <a href="controller?command=home_page">${home}</a>
</div>

</body>

</html>