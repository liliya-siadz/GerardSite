<html>
<head>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <%@ include file="../common/logo.jsp" %>
            <%@ include file="../common/switch-locale.jsp" %>
            <li><a href="home"><fmt:message key="header.link.home.name"/></a></li>
            <li><a href="dogs"><fmt:message key="header.link.dogs.name"/></a></li>
            <li><a href="dogs"><fmt:message key="header.link.puppies.name"/></a></li>
            <li><a href="photos"><fmt:message key="header.link.photos.name"/></a></li>
            <li><a href="sign"><fmt:message key="header.link.make_request.name"/></a></li>
        </ul>
        <%@ include file="../common/sign-panel.jsp" %>
    </div>
</nav>
</body>
</html>
