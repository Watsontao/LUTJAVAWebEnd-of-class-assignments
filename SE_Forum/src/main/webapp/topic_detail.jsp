<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error/500.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="static/js/jquery-3.6.0.min.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrap.bundle.min.js"></script>

    <title>帖子详情</title>
</head>
<body style="background: url(/static/pic/pg2.jpg);background-size:100% 100% ; background-attachment: fixed ">
<nav class="container">
    <ul class="nav nav-tabs">

        <%--这一页要显示的页--%>
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/reply.jsp?topic_id=${param.topic_id}&author=${param.author}">盖楼回复</a>
                </li>

            </c:otherwise>
        </c:choose>

    </ul>
</nav>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>标题</th>
            <th>内容</th>
            <th>作者</th>
            <th>发布时间</th>
        </tr>
        </thead>
        <tr>
            <td>${topic.title}</td>
            <td>${topic.content}</td>
            <td>${topic.username}</td>
            <td>${topic.createTime}</td>
        </tr>

    </table>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>用户</th>
            <th>内容</th>
            <th>回复时间</th>
            <th>楼层</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${replyPage.list}" var="reply">

           <tr>
                <td>${reply.username}</td>
                <td>${reply.content}</td>
                <td>${reply.createTime}</td>
                <td> 第 ${reply.floor} 楼</td>
                <c:if test="${(loginUser!=null && (loginUser.username).equals(reply.username)) ||(loginUser!=null && (loginUser.username).equals(topic.username)) }">
               <td>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/topic?method=DeleteReplyByUsernameAndContentAndAuthor&username=${reply.username}&content=${reply.content}&author=${reply.author}&topic_id=${topic.id}">
                        删除</a>
               </td>
                </c:if>
           </tr>

        </c:forEach>
        </tbody>

    </table>


    <ul class="pagination">
        <c:if test="${replyPage.totalPage>0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${param.topic_id}&page=${replyPage.pageNumber-1}">上一页</a> </li>
            <c:forEach var="i" begin="0" end="${replyPage.totalPage-1}" step="1">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${param.topic_id}&page=${i+1}">${i+1} </a>
                </li>
            </c:forEach>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${param.topic_id}&page=${replyPage.pageNumber+1}">下一页</a> </li>
        </c:if>
    </ul>
</div>
</body>
</html>
