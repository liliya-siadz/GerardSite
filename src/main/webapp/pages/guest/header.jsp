<html>
<head>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <%@ include file="../common/fragment/logo.jsp" %>
            <li><a href="/guest/home"><fmt:message key="header.link.home.name"/></a></li>
            <li><a href="/guest/dogs"><fmt:message key="header.link.dogs.name"/></a></li>
            <li><a href="/guest/puppies"><fmt:message key="header.link.puppies.name"/></a></li>
            <li><a href="${pageContext.request.contextPath}/guest/photos"><fmt:message key="header.link.photos.name"/></a></li>
            <li><a href="/guest/sign"><fmt:message key="header.link.make_request.name"/></a></li>
        </ul>
        <%@ include file="../common/fragment/sign-fragment.jsp" %>
        <%@ include file="../common/fragment/switch-locale-fragment.jsp" %>
    </div>
</nav>
</body>
</html>
