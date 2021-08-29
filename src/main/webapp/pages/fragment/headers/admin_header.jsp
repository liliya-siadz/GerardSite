<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${applicationPath}/css/custom.css">
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
                <form   method="POST"
                        action="${applicationPath}${controllerUrl}">
                    <button
                        type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn headerButton"
                        value="GO_TO_ADMIN_DOGS_PAGE">
                <fmt:message key="page.admin_dogs.header"/>
                    <button type="submit"
                            name="command"
                            class="btn btn-xs navbar-btn headerButton"
                            value="GO_TO_ADMIN_PHOTOS_PAGE">
                        <fmt:message key="page.admin_photos.header"/>
                    <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn headerButton"
                        value="GO_TO_ADMIN_REQUESTS_PAGE">
                    <fmt:message key="page.admin_requests.header"/>
            </form>
            </div>
        <%@ include file="../logout-panel.jsp" %>
    </div>
</nav>
</body>
</html>
