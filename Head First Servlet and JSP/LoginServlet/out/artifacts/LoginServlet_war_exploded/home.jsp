<%--
  Created by IntelliJ IDEA.
  User: welto
  Date: 5/18/2019
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
</head>
<body>
    <h1>Welcome to Home Page</h1>

    <!-- A NEW REQUEST!!!!
            Cannot get request parameters!!!!
            BUT, can get session attribute!!!!
    -->
    Welcome: <%
        //String userName = request.getParameter("userName");
        String userName = (String) session.getAttribute("name");
        out.print(userName);
    %>
</body>
</html>
