<%--
  Created by IntelliJ IDEA.
  User: welto
  Date: 5/18/2019
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <!-- Adding the fail response is the reason we use JSP here. Otherwise, we can use HTML to replace it-->
    <%
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
            out.print(msg);
        }
    %>

    <form action = "/LoginServlet_war_exploded/servlet/doLogin" method="post">
        User Name: <input type="text" name="userName"/><br/>
        Password : <input type="password" name="pwd"/><br/>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
