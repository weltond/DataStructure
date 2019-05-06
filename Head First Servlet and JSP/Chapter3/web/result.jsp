<%--
  Created by IntelliJ IDEA.
  User: HNING2
  Date: 5/6/2019
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>My Beer Advice</title>
</head>

<body>
<h1 align="center">Beer Recommendations JSP</h1>
<p></p>

<!-- Some standard Java sitting inside < % %> tags (this is known as scriptlet code)-->
<%
    List styles = (List)request.getAttribute("styles");
    Iterator it = styles.iterator();
    while (it.hasNext()) {
        out.print("<br> try: " + it.next());
    }
%>
</body>
</html>
