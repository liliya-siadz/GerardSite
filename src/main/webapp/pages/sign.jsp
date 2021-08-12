<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="fragment/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.sign.title"/></title>
</head>
<body>
<%@include file="fragment/headers/header.jsp"%>
<%@include file="fragment/validation_variables.jsp"%>
<form method="POST"
      action="${applicationPath}${controllerUrl}">
<%--    login start --%>
    <label for="loginId"><fmt:message key="page.sign.field.login.label"/></label>
    <input id="loginId"
           type="text"
           name="login"
           required
           pattern="login"
           title="${loginValidationMessage}"
           oninvalid="setCustomValidity('X')"/>
<%--    login end--%>
<%--    password start--%>
    <label for="passwordId"><fmt:message key="page.sign.field.password.label"/></label>
    <input id="passwordId" type="password" name="password" />
<%--    password end  --%>
<%--    login command--%>
    <input type="hidden" name="command" value="LOGIN"/>
    <button type="submit"
            class="btn btn-xs navbar-btn">
        <fmt:message key="header.link.sign_in.name"/>
    </button>
<%--    login command end--%>
</form>
<%@ include file="fragment/footer.jsp" %>
<script type="text/javascript">
    function validLogin(){
        window.alert("hello");
    }
</script>
</body>
</html>
