<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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


<div style="width: auto;height: 150px;"></div>
<!-- Contact Section -->
<section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2>注册一个号，加入我们</h2>
                <hr class="star-primary">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
                <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
                <form action="/user/sendEmail" method="post">
                    <div class="row control-group">
                        <div class="form-group col-xs-12 floating-label-form-group controls">
                            <label>邮箱</label>
                            <input type="email" class="form-control" placeholder="邮箱" id="email" name="email" required
                                   data-validation-required-message="请输入你的邮箱.">
                            <p class="help-block text-danger"></p>
                        </div>
                    </div>
                    <br>
                    <div id="success"></div>
                    <div class="row">
                        <div class="form-group col-xs-12">
                            <button type="submit" class="btn btn-success btn-lg">下一步</button>
                        </div>
                        <c:if test="${!empty message}">
                            <h3><span class="label label-danger">
                                    ${message}
                            </span></h3>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>


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
