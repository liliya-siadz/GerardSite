<html>
<head>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <%@ include file="../logo.jsp" %>
            <li><a href="${pageContext.request.contextPath}/home"><fmt:message key="header.link.home.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/dogs"><fmt:message key="header.link.dogs.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/puppies"><fmt:message key="header.link.puppies.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/photos"><fmt:message key="header.link.photos.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/sign"><fmt:message key="header.link.make_request.name"/></a></li>
        </ul>
        <%@ include file="../sign-fragment.jsp" %>
        <%@ include file="../switch-locale-fragment.jsp" %>
    </div>
</nav>
</body>
</html>
