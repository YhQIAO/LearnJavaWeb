<%--
  Created by IntelliJ IDEA.
  User: qyh
  Date: 2020/1/27
  Time: 6:43 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WelCome Login</title>
    <script>
        window.onload = function() {
            document.getElementById("img").onclick = function () {

                this.src = "/day17/checkCodeServlet?time="+new Date().getTime()
            }
        }


    </script>

    <style>
        div{
            color: red;
        }


    </style>
</head>
<body>
    <form action="/day17/loginServlet" method="post">
        <table align="center">
            <tr>
                <td>username</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>password</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>checkcode</td>
                <td><input type="text" name="checkcode"></td>
            </tr>

            <tr>
                <td colspan="2">
                    <img src="/day17/checkCodeServlet" id = "img">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value = "login">
                </td>
            </tr>

        </table>

        <div><%=request.getAttribute("cc_error") == null ? "":request.getAttribute("cc_error")%></div>
        <div><%=request.getAttribute("login_error")==null?"":request.getAttribute("login_error")%></div>

    </form>
</body>
</html>
