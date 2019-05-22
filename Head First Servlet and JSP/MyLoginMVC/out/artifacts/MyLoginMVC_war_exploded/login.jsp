<%--
  Created by IntelliJ IDEA.
  User: welto
  Date: 5/21/2019
  Time: 9:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Log In</title>
</head>
<body>

<%--<%--%>
    <%--String msg = (String) request.getAttribute("wrongUser");--%>
    <%--if (msg != null) {--%>
        <%--out.print("wrong");--%>
    <%--}--%>
<%--%>--%>
<c:if test="${not empty wrongUser}">
    ${wrongUser}
</c:if>

<form action="${pageContext.request.contextPath}/servlet/loginServlet" method="post">

    User Name : <input type="text" name="username"/>

    <br>
    Password  : <input type="password" name="password"/>
    <br>
    <input type="submit" value="LogIn">
</form>
</body>
</html>
