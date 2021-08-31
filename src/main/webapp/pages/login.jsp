<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"
         pageEncoding="UTF-8" language="java" %>
<%@ include file="fragment/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.login.title"/></title>
</head>
<body>
<%@include file="fragment/headers/header.jsp"%>
<c:if test="${not empty isUserFound}">
    <c:if test="${not isUserFound}">
        <p style="color: red"><fmt:message key="page.login.command.authorization.result"/></p>
    </c:if>
</c:if>
<%@include file="fragment/forms/login_form.jsp"%>
<%@include file="fragment/validation_maps/login_validation_map.jsp"%>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
