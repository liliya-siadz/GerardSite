<html>
<head>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <%@ include file="../logo.jsp" %>
            <li><a href="${pageContext.request.contextPath}/home"><fmt:message key="header.link.home.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/dogs?command=GET_ALL_DOGS"><fmt:message key="header.link.dogs.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/puppies"><fmt:message key="header.link.puppies.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/photos"><fmt:message key="header.link.photos.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/sign"><fmt:message key="header.link.make_request.name"/></a></li>
        </ul>
        <%@ include file="../sign_in_up_button.jsp" %>
        <%@ include file="../switch_locale_panel.jsp" %>
    </div>
</nav>
</body>
</html>
