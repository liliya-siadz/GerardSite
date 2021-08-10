<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/7/2021
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<body>
<%--switch locale panel--%>
<div class="btn-group btn-group-xs" role="locale-panel">
<form method="POST"
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
