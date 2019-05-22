<%--
  Created by IntelliJ IDEA.
  User: welto
  Date: 5/21/2019
  Time: 6:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <c:if test="${empty user}">
    <a href="login.jsp">LogIn</a>
    <a href="reg.jsp">Register</a>
  </c:if>

  <c:if test="${not empty user}">
    Welcome: ${user.username}  <a href="${pageContext.request.contextPath}/login.jsp">Log Out</a>

  </c:if>

  </body>
</html>
