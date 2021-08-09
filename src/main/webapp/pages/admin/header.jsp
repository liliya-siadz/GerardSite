<html>
  <head>
    <title>Admin header</title>
  </head>
  <body>
  <%--locale switch panel--%>
  <form method="post"
        action="${applicationPath}${controllerUrl}">
    <button type="submit"
            name="command"
            value="SWITCH_LOCALE_TO_EN">
      EN
    </button>
    <button type="submit"
            name="command"
            value="SWITCH_LOCALE_TO_BE">
      BE
    </button>
    <button type="submit"
            name="command"
            value="SWITCH_LOCALE_TO_RU">
      RU
    </button>
  </form>
  <%--locale switch panel----%>
  <%@ include file="../common/fragment/footer.jsp" %>
  </body>
</html>
