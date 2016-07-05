<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>HOME</title>

    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${ctx}/css/freelancer.css" rel="stylesheet">

    <link href="${ctx}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.useso.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="http://fonts.useso.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body id="page-top" class="index">
<!-- Navigation -->
<jsp:include page="commons/header.jsp"/>

<div style="width: auto;height:130px;"></div>

<div style="width: 100%;height: 300px;margin-top: 300px;">
    <h1 style="margin-left: 800px;">${message}</h1>
</div>


<!-- Footer -->
<jsp:include page="commons/footer.jsp"/>


<!-- jQuery -->
<script src="${ctx}/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${ctx}/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="${ctx}/js/classie.js"></script>
<script src="${ctx}/js/cbpAnimatedHeader.js"></script>

<!-- Contact Form JavaScript -->
<script src="${ctx}/js/jqBootstrapValidation.js"></script>
<script src="${ctx}/js/contact_me.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${ctx}/js/freelancer.js"></script>

</body>
</html>
