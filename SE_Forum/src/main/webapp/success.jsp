<%--
  Created by IntelliJ IDEA.
  User: vincent
  Date: 2022/11/6
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error/500.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">

    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/jquery-3.6.0.min.js"></script>
    <title>上传成功</title>
</head>

<%--<body background="static/pic/999.jpg" size="">--%>
<body style="background: url(/static/pic/pg2.jpg);background-size:100% 100% ; background-attachment: fixed ">

<nav class="container">
    <ul class="nav nav-tabs">
        <%--上面的种类--%>
        <c:forEach items="${categoryList}" var="category">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/topic?method=list&c_id=${category.id}"> ${category.name}</a>
            </li>
        </c:forEach>
        <%--首先判断loginUser是否为空 若是--%>
        <c:choose>
            <c:when test="${empty loginUser}">    <%--注册 登录  按钮--%>
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/register.jsp">注册</a>
                </li>
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/login.jsp">登录</a>
                </li>
            </c:when>


            <%--若已经登录了 则--%>
            <c:otherwise>
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user?method=logout">注销</a>
                </li>

                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/sign?method=sign_in">签到</a>
                </li>

                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="/topic?method=list_2&c_id=1">${loginUser.username}</a>
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





<br class="container" >
<table class="right"  border="1" align="center">

    上传成功!!!

</table>

</body>
</html>
