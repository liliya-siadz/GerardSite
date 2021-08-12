<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<body>
<%--sign in panel start--%>
<div class="d-block nav navbar-right" style="margin-right:45px;">
<a href="${pageContext.request.contextPath}/sign" class="btn btn-danger navbar-btn btn-xs" style="margin-bottom:30px">
    <fmt:message key="header.link.sign_in.name"/>
    |
    <fmt:message key="header.link.sign_up.name"/>
</a>
</div>
<%--sign in panel end  --%>
</body>
</html>
