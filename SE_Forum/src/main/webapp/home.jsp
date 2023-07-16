<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@page errorPage="error/500.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background: url(/static/pic/bg1.png);background-size:100% 100% ; background-attachment: fixed ">
<%--打开第一个topic--%>
<jsp:forward page="/topic?method=list&c_id=1"></jsp:forward>


</body>
</html>
 n