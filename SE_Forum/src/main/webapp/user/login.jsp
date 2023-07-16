<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>兰州理工大学软件开发论</title>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/jquery.min.js"></script>

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
    </ul>
</nav>



<div style="margin-top: 20px" class="container">
    <!-- 登录表单 -->
    <form style="margin-left:100px;margin-top:20px;" action="${pageContext.request.contextPath}/user?method=login" method="post">
        <div class="form-group">
            <label>手机号：</label>
            <input type="text" class="form-control" name="phone" style="display:inline;width:200px;"
                   autocomplete="off"/>
        </div>
        <div style="margin-top: 20px" class="form-group">
            <label style="display:inline;">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
            <input type="password" class="form-control" name="pwd" style="display:inline;width:200px;" autocomplete="off"/>
        </div>

        <div class="form-group">
            <div class="col-3">
                ${msg}
            </div>
        </div>



        <button style="margin-top: 20px" type="submit" class="btn btn-primary">登录</button>

        <button style="margin-top: 20px" type="button" class="btn btn-primary"
                onclick="location.href=('/user/register.jsp')">注册
        </button>
    </form>
</div>
</body>
</html>
