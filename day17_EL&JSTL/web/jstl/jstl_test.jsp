<%@ page import="club.yhqiao.web.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>test</title>
</head>
<body>

<%
    List list = new ArrayList();
    list.add(new User("qyh",20,new Date()));
    list.add(new User("xjy",20,new Date()));
    list.add(new User("cjk",20,new Date()));
    request.setAttribute("list",list);
%>

<table align="center" border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
        <th>date</th>
    </tr>

    <c:forEach items="${list}" var="user" varStatus="s">

        <c:if test="${s.count%2 == 0}">
            <tr bgcolor="red">
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:if>

        <c:if test="${s.count%2 == 1}">
            <tr bgcolor="blue">
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:if>




    </c:forEach>
</table>




</body>
</html>
