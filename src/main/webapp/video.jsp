<%@ page import="java.util.Date" %>
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

<div style="width: auto;height:130px;"></div>

<div style="width: 100%;height: 50px;margin-top: 50px;">
    <h1 style="margin-left: 50px;">${course.name},${course.introduce},${course.totalTime}</h1>
</div>

<!--视频播放区-->
<c:forEach items="${videoList}" var="video">
    <embed
            src="${video.videoUrl}"
            type="application/x-shockwave-flash"
            allowscriptaccess="always"
            allowfullscreen="true"
            wmode="opaque"
            width="100%"
            height="600"
            id="tudouPlayer">
    </embed>
</c:forEach>


<!--评论区-->
<br/>
<div class="alert alert-success"><h4>评论区,给我们提点意见吧</h4></div>
<br/>
<nav style="width: 80%;margin-left: 200px;">

    <c:forEach items="${commentVOs}" var="comment">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    ${comment.useremail} &nbsp;&nbsp;&nbsp;${comment.createTime}
                </h3>
            </div>
            <div class="panel-body">
                ${comment.text}
            </div>
        </div>
    </c:forEach>


    <!--分页-->
    <nav style="text-align: center">
        <ul class="pagination pagination-lg">
            <li>
                <c:choose>
                    <c:when test="${coursePage.pageNo eq 1}">
                        <a href="${ctx}/course/1/list" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${ctx}/course/${coursePage.pageNo-1}/list" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li><a href="${ctx}/course/1/list">1</a></li>
            <c:forEach var="i" begin="2" end="${coursePage.totalPage}">
                <li><a href="${ctx}/course/${i}/list">${i}</a></li>
            </c:forEach>
            <li>
                <c:choose>
                    <c:when test="${coursePage.pageNo eq coursePage.totalPage}">
                        <a href="${ctx}/course/${coursePage.totalPage}/list" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${ctx}/course/${coursePage.pageNo+1}/list" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:otherwise>
                </c:choose>

            </li>
        </ul>
    </nav>
</nav>

<nav style="width: 80%;margin-left: 200px;">
    <form role="form" action="${ctx}/course/addComment" method="post">
        <div class="form-group">
            <label>请留下您宝贵的意见，让我们能做得更好</label>
            <textarea class="form-control" rows="3" name="text"></textarea>
        </div>
        <input type="text" name="courseId" value="${course.id}" style="display: none">
        <div style="margin-left: 1400px;">
            <button type="submit" class="btn btn-info">确认提交</button>
        </div>
    </form>
</nav>
<br/><br/>

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
