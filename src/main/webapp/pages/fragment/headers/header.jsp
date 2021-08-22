<html>
<head>
    <style>
        a.headerLink:hover {
            color: red;
            text-decoration: none;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <%@ include file="../logo.jsp" %>
        <%@ include file="../switch_locale_panel.jsp" %>
        <a href="${pageContext.request.contextPath}/home"
           role="button"
           class="btn btn-xs navbar-btn headerLink">
            <fmt:message key="page.home.header"/>
        </a>
        <div class="btn-group btn-group-xs" role="header">
            <form method="GET"
                  action="${applicationPath}${controllerUrl}">
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn"
                        value="GET_ALL_DOGS">
                    <fmt:message key="page.dogs.header"/>
                </button>
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn"
                        value="GET_ALL_PUPPIES">
                    <fmt:message key="page.puppies.header"/>
                </button>
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn"
                        value="GET_ALL_PHOTOS">
                    <fmt:message key="page.photos.header"/>
                </button>
            </form>
        </div>
        <a href="${pageContext.request.contextPath}/login"
           role="button"
           class="btn btn-xs navbar-btn headerLink">
            <fmt:message key="page.login.header"/>
        </a>
    </div>
</nav>
</body>
</html>
