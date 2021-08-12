<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="fragment/include.jsp" %>
<html>
<head>
    <title><fmt:message key="page.sign.title"/></title>
</head>
<body>
<%@include file="fragment/headers/header.jsp"%>

<form method="POST"
      action="${applicationPath}${controllerUrl}">
    <button type="submit"
            name="command"
            value="LOGIN"
            class="btn btn-xs navbar-btn">
        <fmt:message key="header.link.sign_in.name"/>
    </button>
</form>

<%@ include file="fragment/footer.jsp" %>
</body>
</html>
