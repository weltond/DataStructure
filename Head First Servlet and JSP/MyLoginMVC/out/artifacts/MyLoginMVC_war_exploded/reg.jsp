<%--
  Created by IntelliJ IDEA.
  User: welto
  Date: 5/21/2019
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/servlet/regServlet" method="post">
        User Name : <input type="text" name="username"/><br>
        Password  : <input type="password" name="password"/><br>
        Confirm   : <input type="password" name="repassword"/><br>
        Email     : <input type="text" name="email"/><br>
        Birthday  : <input type="text" name="birthday"/><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
