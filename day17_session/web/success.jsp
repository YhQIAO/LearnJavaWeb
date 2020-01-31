<%--
  Created by IntelliJ IDEA.
  User: qyh
  Date: 2020/1/27
  Time: 7:06 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>welcome <%=request.getSession().getAttribute("user")%></h1>
</body>
</html>
