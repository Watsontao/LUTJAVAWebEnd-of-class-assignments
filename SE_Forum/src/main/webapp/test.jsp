<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error/500.jsp" %>
<html>




<head>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>xcLeigh - 个人空间</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <link rel="stylesheet" href="css/myspace.css" />--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">

    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/jquery-3.6.0.min.js"></script>
    <title>index</title>
</head>

<body>

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

<%--<div class="divConBg"></div>--%>

<div class="divCon">
    <div class="divConLr">
        <div style="width:38px; border-right:1px dotted #464944;height:100%;" align="left">
            <div id="lCon1" class="divConLr_L" style="background-color:orange; padding:10px;">我<br/>的<br/>帖<br/>子</div>
            <div id="lCon2" class="divConLr_L">我<br/>的<br/>回<br/>复</div>
        </div>

        <div class="container" align="right">
            <table class="table table-striped" border="1">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>内容</th>
                    <th>作者</th>
                    <th>发布时间</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${topicPage2.list}" var="topic">
                    <tr>
                        <td>${topic.title}</td>
                        <td>${topic.content}</td>
                        <td>${topic.username}</td>
                        <td>${topic.createTime}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}" >详情</a>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </br>
        </div>
        <div id="content2" class="container">
            <thead>
            <tr>
                <th>原帖作者</th>
                <th>内容</th>
                <th>回复时间</th>
                <th>楼层</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${replyPage_2.list}" var="reply">
                <tr>
                    <td>${reply.author}</td>
                    <td>${reply.content}</td>
                    <td>${reply.createTime}</td>
                    <td> 第 ${reply.floor} 楼</td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
        </div>
    </div>

</div>
<div class="bottomdiv">
    <div>
        <iframe src="other/music/index.html" style="border:0px;padding:0px;margin:0px;overflow:hidden;width:100%;"></iframe>
    </div>
    <span style="cursor:pointer">用户名</span>
    <span style="cursor:pointer">个人空间</span>
    | 上线了 ：<span id="span_dt_dt"></span>
</div>
</body>
<script src="./js/myspace.js" type="text/javascript"></script>
</html>
