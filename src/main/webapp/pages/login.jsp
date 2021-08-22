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
    <title><fmt:message key="page.login.title"/></title>
</head>
<body>
<%@include file="fragment/headers/header.jsp"%>
<%@include file="fragment/validation_variables.jsp"%>

<%--1 LOGIN--%>

<c:if test="${not empty sessionScope.isUserFound}">
    <c:if test="${not sessionScope.isUserFound}">
        <p style="color: red"><fmt:message key="page.login.authorization.result"/></p>
    </c:if>
</c:if>
<form method="POST"
      action="${applicationPath}${controllerUrl}">

<%--    email start --%>
    <label for="emailId"><fmt:message key="page.login.field.email.label"/></label>
    <input id="emailId"
           type="text"
           name="email"
<%--           required--%>
<%--           pattern="${emailPattern}"--%>
<%--           title="${emailValidationMessage}"--%>
<%--           maxlength="${emailMaxLength}"--%>
<%--           minlength="${emailMinLength}"--%>
<%--           oninvalid="setCustomValidity('X')"/>--%>
        />
<%--    email end--%>

<%--    password start--%>
    <label for="passwordId"><fmt:message key="page.login.field.password.label"/></label>
    <input id="passwordId"
           type="password"
           name="password"/>
<%--    password end  --%>

<%--    LOGIN command--%>
    <input type="hidden" name="command" value="LOGIN"/>
    <button type="submit"
            class="btn btn-xs navbar-btn">
        <fmt:message key="page.login.button.login.name"/>
    </button>
<%--    LOGIN command end--%>
</form>
<c:if test="${not empty sessionScope.loginValidationMap}">
    <c:if test="${not sessionScope.loginValidationMap.email}">
        <fmt:message key="page.login.field.email.validation.message"/>
    </c:if>
</c:if>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>