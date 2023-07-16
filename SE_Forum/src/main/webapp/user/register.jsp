<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>兰州理工大学软件开发论</title>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>

</head>
<body style="background: url(/static/pic/pg2.jpg);background-size:100% 100% ; background-attachment: fixed ">
<script type="text/javascript">
    function fun(){
        var x = document.getElementsByName("pwd")[0].value;
        var y = document.getElementsByName("pwd2")[0].value;
        if(x !== y){
            alert("the pwd are different!");
            location.reload();

        }
    }
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
        var url = "/userRegisterServlet?phone="+phone;
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
        var url = "/userRegisterServlet?username="+username;
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
    </ul>
</nav>

<div style="margin-top: 10px" class="container">
    <form action="${pageContext.request.contextPath}/user?method=register" method="post">
        <div class="row">
            <div class="col-1 offset-2">
                <label class="control-label">手机号</label>
            </div>
            <div class="col-2">
                <input type="text" class="form-control" name="phone" placeholder="手机号" onblur="ajaxP()" id="phone">
            </div>
            <div class="col-2" id="phonemsg"></div>
            <div class="col-5"> </div>
        </div>
        <div class="row">
            <div class="col-1 offset-2">
                <label class="control-label">用户名</label>
            </div>
            <div class="col-2">
                <input type="text" class="form-control" name="username" placeholder="用户名" onblur="ajaxN()" id="username">
            </div>
            <div class="col-2"id="usernamemsg"> </div>
            <div class="col-5"> </div>
        </div>
        <div class="row">
            <div class="col-1 offset-2">
                <label class="control-label">密码</label>
            </div>
            <div class="col-2">
                <input type="password" class="form-control" name="pwd" placeholder="密码">
            </div>
            <div class="col-7"> </div>
        </div>
        <div class="row">
            <div class="col-1 offset-2">
                <label class="control-label">确认密码</label>
            </div>
            <div class="col-2">
                <input type="password" class="form-control" name="pwd2" placeholder="确认密码">
            </div>
            <div class="col-7"> </div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="col-1 offset-2">
                <label class="control-label">性别</label>
            </div>
            <div class="col-1">
                男<input type="radio" name="sex"  value="1" checked>
            </div>

            <div class="col-1">
                女<input type="radio" name="sex"  value="2" >
            </div>
            <div class="col-7"> </div>
        </div>
        <div class="row">
            <div class="col-10 offset-2" style="">
                <button type="submit" class="btn btn-primary" onclick="fun()">提交</button>
                <button type="reset" class="btn btn-primary">重置</button>
            </div>
        </div>
    </form>



</div>
</body>
</html>