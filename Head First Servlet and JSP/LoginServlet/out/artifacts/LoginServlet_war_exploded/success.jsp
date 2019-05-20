<%--
  Created by IntelliJ IDEA.
  User: welto
  Date: 5/18/2019
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    Welcome: <%
        //String userName = request.getParameter("userName");
        String userName = (String) session.getAttribute("name");
        out.print(userName);
    %>

    <br>
    Password: ${param["pwd"]}
    <br>
    Wrong Msg: ${el}
    <!-- A NEW REQUEST!!!!
            Cannot get above request parameters!!!!
            BUT, can get session attribute!!!!
    -->
<a href="/LoginServlet_war_exploded/home.jsp">HOME</a>
</body>
</html>
