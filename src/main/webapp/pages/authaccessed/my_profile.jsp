<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../fragment/include.jsp" %>
<html>
<head>
    <title><fmt:message key="page.my_profile.title"/></title>
</head>
<body>
<c:choose>
    <c:when test="${role eq 'client'}"><%@include file="../fragment/headers/client_header.jsp" %></c:when>
    <c:when test="${role eq 'admin'}"><%@include file="../fragment/headers/admin_header.jsp" %></c:when>
</c:choose>
<%@ include file="../fragment/footer.jsp" %>
</body>
</html>
