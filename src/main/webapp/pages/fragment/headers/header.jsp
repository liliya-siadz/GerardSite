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
        <a href="${pageContext.request.contextPath}/home"
           role="button"
           class="btn btn-xs navbar-btn headerLink">
            <fmt:message key="header.link.home.name"/>
        </a>
        <div class="btn-group btn-group-xs" role="header">
            <form method="GET"
                  action="${applicationPath}${controllerUrl}">
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn"
                        value="GET_ALL_DOGS">
                    <fmt:message key="header.link.dogs.name"/>
                </button>
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn"
                        value="GET_ALL_PUPPIES">
                    <fmt:message key="header.link.puppies.name"/>
                </button>
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn"
                        value="GET_ALL_PHOTOS">
                    <fmt:message key="header.link.photos.name"/>
                </button>
            </form>
        </div>
        <a href="${pageContext.request.contextPath}/sign"
           role="button"
           class="btn btn-xs navbar-btn headerLink">
            <fmt:message key="header.link.make_request.name"/>
        </a>
        <%@ include file="../sign_in_up_button.jsp" %>
        <%@ include file="../switch_locale_panel.jsp" %>
    </div>
</nav>
</body>
</html>
