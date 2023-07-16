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
    <title>主页</title>
</head>




<body style="background: url(/static/pic/backgrounnd.jpg);background-size:100% 100% ; background-attachment: fixed ">




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

                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="#">当前在线人数：${Online_number}</a>
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

                <li class="nav-item" style="float: right">
                    <a class="nav-link" href="#">当前在线人数：${Online_number}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>


<%--详细内容--%>
<div class="container">
    <table class="table table-striped">
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
        <c:forEach items="${topicPage.list}" var="topic">
            <tr>
                <td>${topic.title}</td>
                <td>${topic.content}</td>
                <td>${topic.username}</td>
                <td>${topic.createTime}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}&author=${topic.username}" >详情</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>




    <%--分页设计--%>
    <ul class="pagination">
        <c:if test="${topicPage.totalPage>0}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/topic?method=list&c_id=${param.c_id}&page=${(topicPage.pageNumber-1>0)?topicPage.pageNumber-1:1}">上一页</a> </li>
            <c:forEach var="i" begin="0" end="${topicPage.totalPage-1}" step="1">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/topic?method=list&c_id=${param.c_id}&page=${i+1}">${i+1} </a>    <%--下面这个${i+1}的作用是显示1、2、3页--%>
                </li>
            </c:forEach>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/topic?method=list&c_id=${param.c_id}&page=${((topicPage.pageNumber+1)>topicPage.totalPage)?topicPage.totalPage:(topicPage.pageNumber+1)}">下一页</a> </li>
        </c:if>
    </ul>

</div>

</body>

</html>
<%--${pageContext.request.contextPath}是JSP中取得绝对路径的方法，等价于<%=request.getContextPath()%>
                  也就是取出部署的应用程序名，即获取当前的项目的名称--%>
<%--${param.c_id} 与输入有关，相对于 request.getParameter("c_id")。意思是获得输入的参数c_id。 --%>