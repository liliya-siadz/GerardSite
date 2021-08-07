<html>
<head>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <%@ include file="../common/switch-locale.jsp" %>
            <li><a href="home"><fmt:message key="menu_point.home.name"/></a></li>
            <li><a href="dogs"><fmt:message key="menu_point.dogs.name"/></a></li>
            <li><a href="dogs"><fmt:message key="menu_point.puppies.name"/></a></li>
            <li><a href="photos"><fmt:message key="menu_point.photos.name"/></a></li>
            <li><a href="sign"><fmt:message key="menu_point.make_request.name"/></a></li>
        </ul>
        <div class="d-block nav navbar-right" style="margin-right:45px;">
                <a href="sign" class="btn btn-danger navbar-btn">
                    <fmt:message key="menu_point.sign_in.name"/>
                    |
                    <fmt:message key="menu_point.sign_up.name"/>
                </a>
        </div>
    </div>
</nav>
</body>
</html>
