<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/pages/include.jsp" %>
<html>
    <head>
    <title><fmt:message key="page.home.title"/></title>
    </head>
    <body>
    Home page.
    <form method="post"
          action="${applicationPath}${servletUrl}">
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
        <a href="home">home</a>
        <a href="dogs">dogs</a>
    </form>
    </body>
</html>
