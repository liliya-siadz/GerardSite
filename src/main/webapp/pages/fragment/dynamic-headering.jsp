<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/10/2021
  Time: 1:45 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<body>
<c:choose>
    <c:when test="${role eq 'admin'}"><%@include file="headers/admin_header.jsp" %></c:when>
    <c:when test="${role eq 'client'}"><%@include file="headers/client_header.jsp" %></c:when>
    <c:otherwise><%@include file="headers/header.jsp" %></c:otherwise>
</c:choose>
</body>
</html>
