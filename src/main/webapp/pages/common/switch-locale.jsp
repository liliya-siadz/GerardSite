<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/7/2021
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%--switch locale panel--%>
<li>
    <a class="nav navbar-brand" href="home">
        <img src="${applicationPath}/logo.png"
             alt="GERARD"
             width="50"
             style="margin-bottom: 45px;">
    </a>
</li>
<div class="btn-group btn-group-xs nav navbar-left" role="locale-panel">
<form method="post"
      action="${applicationPath}${controllerUrl}">
    <button type="submit"
            name="command"
            class="btn btn-xs navbar-btn"
            value="SWITCH_LOCALE_TO_EN">
        EN
    </button>
    <button type="submit"
            name="command"
            class="btn btn-xs navbar-btn"
            value="SWITCH_LOCALE_TO_BE">
        BE
    </button>
    <button type="submit"
            name="command"
            class="btn btn-xs navbar-btn"
            value="SWITCH_LOCALE_TO_RU">
        RU
    </button>
</form>
</div>
<%--switch locale  panel----%>
</body>
</html>
