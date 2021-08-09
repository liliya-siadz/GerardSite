<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"  %>
<%@ page isELIgnored="false" %>
<%@ include file="fragment/include.jsp" %>
<html>
<head>
    <title><fmt:message key="page.sign.title"/></title>
</head>
<body>
<c:set var="role" value=""/>
<c:choose>
    <c:when test="${role eq 'admin'}"><%@include file="fragment/headers/admin_header.jsp" %></c:when>
    <c:when test="${role eq 'client'}"><%@include file="fragment/headers/client_header.jsp" %></c:when>
    <c:otherwise><%@include file="fragment/headers/header.jsp" %></c:otherwise>
</c:choose>
<%@ include file="fragment/footer.jsp" %>
</body>
</html>
