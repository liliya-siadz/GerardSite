<html>
<head>
</head>
<body>
<%--logout panel--%>
<div class="btn-group btn-group-xs" role="logout-panel">
    <form method="POST"
          action="${applicationPath}${controllerUrl}">
        <button type="submit"
                name="command"
                class="btn btn-xs navbar-btn"
                value="LOGOUT">
            <fmt:message key="admin.button.logout.name"/>
        </button>
    </form>
</div>
<%--logout locale  panel----%>
</body>
</html>
