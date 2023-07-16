<%--
  Created by IntelliJ IDEA.
  User: vincent
  Date: 2022/11/4
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>签到页面</title>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>

</head>

<body style="background: url(/static/pic/pg2.jpg);background-size:100% 100% ; background-attachment: fixed ">
<script type="text/javascript">
    function creatXHR() {
        var xmlhttp;
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest();
        }else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlhttp;
    }

    function ajaxP(){
        var phone = document.getElementById("phone").value;
        xhr = creatXHR();
        var url = "/SE_Forum_war_exploded/userRegisterServlet?phone="+phone;
        xhr.open("POST",url,true);
        xhr.onreadystatechange=function m() {
            if(xhr.readyState === 4 && xhr.status === 200){
                document.getElementById("phonemsg").innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    function ajaxN(){
        var username = document.getElementById("username").value;
        xhr = creatXHR();
        var url = "/SE_Forum_war_exploded/userRegisterServlet?username="+username;
        xhr.open("GET",url,true);
        xhr.onreadystatechange=function m() {
            if(xhr.readyState === 4 && xhr.status === 200){
                document.getElementById("usernamemsg").innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }


</script>
<nav class="container">
    <ul class="nav nav-tabs">
        <c:forEach items="${categoryList}" var="category">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/topic?method=list&c_id=${category.id}"> ${category.name}</a>
            </li>
        </c:forEach>

        <c:choose>
            <c:when test="${empty loginUser}">
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/register.jsp">注册</a>
                </li>
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/login.jsp">登录</a>
                </li>
            </c:when>

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
                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="${pageContext.request.contextPath}/reply.jsp?topic_id=${param.topic_id}">盖楼回复</a>
                </li>

            </c:otherwise>
        </c:choose>
    </ul>
</nav>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>今日排名</th>
            <th>最近签到日期</th>
            <th>用户名</th>
            <th>当月签到天数</th>
            <th>总签到天数</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${sign_inList}" var="signin">
            <tr>
                <td>${signin.number}</td>
                <td>${signin.date}</td>
                <td>${signin.username}</td>
                <td>${signin.monDay}</td>
                <td>${signin.sumDay}</td>


            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>

