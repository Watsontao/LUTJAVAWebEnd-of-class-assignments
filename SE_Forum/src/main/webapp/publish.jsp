<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error/500.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">

    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/jquery-3.6.0.min.js"></script>
    <title>发布帖子</title>
</head>
<body style="background: url(/static/pic/pg2.jpg);background-size:100% 100% ; background-attachment: fixed ">
<nav class="container">
    <ul class="nav nav-tabs">
        <c:forEach items="${categoryList}" var="category">
            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/topic?method=list&c_id=${category.id}"> ${category.name}</a>
            </li>
        </c:forEach>

        <c:choose>
            <%--首先检查是否有用户登录，没有的话就先登录，有的话才能继续查看--%>
            <c:when test="${empty loginUser}">
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/register.jsp">注册</a>
                </li>
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/login.jsp">登录</a>
                </li>
            </c:when>

            <%--首先检查是否有用户登录，没有的话就先登录，有的话才能继续查看--%>
            <c:otherwise>
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user?method=logout">注销</a>
                </li>


                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/sign?method=sign_in">签到</a>
                </li>

                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="#">${loginUser.username}</a>
                </li>

                <li class="nav-item" style="float: right">
                    <img src="${loginUser.img}" class="img-circle" width="25px" height="25px" style="margin-top: 8.5px">
                </li>

                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/publish.jsp">发布主题</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>
<div style="margin-top: 100px" class="container">

    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/topic?method=addTopic"
          method="post">

        <div class="form-group">
            <label class="col-sm-2 control-label">标题</label>
            <div class="col-lg-3">
                <input type="text" class="form-control" name="title" placeholder="标题">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">分类</label>
            <div class="col-lg-3">
                <select class="form-control" name="c_id">

                    <c:forEach items="${categoryList}" var="category">
                        <option value="${category.id}">${category.name} </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">内容</label>
            <div class="col-lg-8">

                <textarea class="form-control" name="content" placeholder="请输入内容"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">发布主题</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
