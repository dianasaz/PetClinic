<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dianasaz
  Date: 23.07.2019
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:message bundle="${language}" key="home" var="home"/>
<fmt:message bundle="${language}" key="about" var="about"/>
<fmt:message bundle="${language}" key="services" var="services"/>
<fmt:message bundle="${language}" key="doctors" var="doctors"/>
<fmt:message bundle="${language}" key="profile" var="profile"/>
<fmt:message bundle="${language}" key="ourpets" var="ourpets"/>
<html lang="${language}">
<head>
    <!--bootstarp-css-->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!--/bootstarp-css -->
    <!--css-->
    <link rel="stylesheet" href="css/style_new.css" type="text/css" media="all"/>
    <!--/css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Petcare Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript">
         addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }


    </script>
    <link href="css/hover.css" rel="stylesheet" media="all">
    <!--fonts-->
    <link href='http://fonts.googleapis.com/css?family=Arimo:400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
    <!--/fonts-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <script src="js/responsiveslides.min.js"></script>
</head>
<body>
<ul class="cl-effect-21">
    <li><a href="controller?command=home_page" class="active">${home}</a></li>
    <li><a href="controller?command=profile">${profile}</a></li>
    <li><a href="controller?command=watch_service">${services}</a></li>
    <li><a href="controller?command=watch_doctor">${doctors}</a></li>
</ul>
<!--script-nav -->
<script>
					$("span.menu-info").click(function(){
						$("ul.cl-effect-21").slideToggle("slow" , function(){
						});
					});


            </script>

<!-- /script-nav -->
<div class="clearfix"></div>
</body>
</html>
