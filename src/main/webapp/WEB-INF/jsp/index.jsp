<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!--引入jstl-->
<%@include file="common/tag.jsp" %>
<%@include file="common/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="navbar navbar-inverse">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span
                                class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
                        <a href="#" class="brand">网站名</a>
                        <div class="nav-collapse collapse navbar-responsive-collapse">
                            <ul class="nav">
                                <li class="active">
                                    <a href="#">主页</a>
                                </li>
                                <li>
                                    <a href="#">链接</a>
                                </li>
                                <li>
                                    <a href="#">链接</a>
                                </li>
                                <li class="dropdown">
                                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong
                                            class="caret"></strong></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">下拉导航1</a>
                                        </li>
                                        <li>
                                            <a href="#">下拉导航2</a>
                                        </li>
                                        <li>
                                            <a href="#">其他</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li class="nav-header">
                                            标签
                                        </li>
                                        <li>
                                            <a href="#">链接1</a>
                                        </li>
                                        <li>
                                            <a href="#">链接2</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="nav pull-right">
                                <li>
                                    <a href="#">右边链接</a>
                                </li>
                                <li class="divider-vertical">
                                </li>
                                <li class="dropdown">
                                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">下拉菜单<strong
                                            class="caret"></strong></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="#">下拉导航1</a>
                                        </li>
                                        <li>
                                            <a href="#">下拉导航2</a>
                                        </li>
                                        <li>
                                            <a href="#">其他</a>
                                        </li>
                                        <li class="divider">
                                        </li>
                                        <li>
                                            <a href="#">链接3</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>

            </div>
            <div class="carousel slide" id="carousel-966662">
                <ol class="carousel-indicators">
                    <li data-slide-to="0" data-target="#carousel-966662">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-966662" class="active">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-966662">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item">
                        <img alt="" src="img/1.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                棒球
                            </h4>
                            <p>
                                棒球运动是一种以棒打球为主要特点，集体性、对抗性很强的球类运动项目，在美国、日本尤为盛行。
                            </p>
                        </div>
                    </div>
                    <div class="item active">
                        <img alt="" src="img/2.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                冲浪
                            </h4>
                            <p>
                                冲浪是以海浪为动力，利用自身的高超技巧和平衡能力，搏击海浪的一项运动。运动员站立在冲浪板上，或利用腹板、跪板、充气的橡皮垫、划艇、皮艇等驾驭海浪的一项水上运动。
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt="" src="img/3.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                自行车
                            </h4>
                            <p>
                                以自行车为工具比赛骑行速度的体育运动。1896年第一届奥林匹克运动会上被列为正式比赛项目。环法赛为最著名的世界自行车锦标赛。
                            </p>
                        </div>
                    </div>
                </div>
                <a data-slide="prev" href="#carousel-966662" class="left carousel-control">‹</a> <a data-slide="next"
                                                                                                    href="#carousel-966662"
                                                                                                    class="right carousel-control">›</a>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span2">
            <p class="text-info text-left">
                <em>Git</em>是一个分布式的版本控制系统，最初由<strong>Linus Torvalds</strong>编写，用作Linux内核代码的管理。在推出后，Git在其它项目中也取得了很大成功，尤其是在Ruby社区中。
            </p>
        </div>
        <div class="span6">
            <img alt="140x140" src="img/a.jpg" class="img-rounded"/><img alt="140x140" src="img/a.jpg"
                                                                         class="img-rounded"/>
        </div>
        <div class="span4">
            <div class="hero-unit">
                <h1>
                    Hello, world!
                </h1>
                <p>
                    这是一个可视化布局模板, 你可以点击模板里的文字进行修改, 也可以通过点击弹出的编辑框进行富文本修改. 拖动区块能实现排序.
                </p>
                <p>
                    <a class="btn btn-primary btn-large" href="#">参看更多 »</a>
                </p>
            </div>
        </div>
    </div>
</div>


</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>
