<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/include.jsp" %>
<%--TO-DO: switch headers? where store role--%>
<%@ include file="header.jsp" %>
<html>
    <head>
    <title><fmt:message key="page.home.title"/></title>
    </head>
    <body>
    Home page.
    <div class="container">
        <a href="home">home</a>
        <a href="dogs">dogs</a>
    </div>
    </body>
<%@ include file="../common/footer.jsp" %>
</html>
