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

<c:if test="${not empty sessionScope.isUserFound}">
    <c:if test="${not sessionScope.isUserFound}">
        <p style="color: red"><fmt:message key="page.sign.authorization.result"/></p>
    </c:if>
</c:if>
<form method="POST"
      action="${applicationPath}${controllerUrl}">

<%--    email start --%>
    <label for="emailId"><fmt:message key="page.sign.field.email.label"/></label>
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
    <label for="passwordId"><fmt:message key="page.sign.field.password.label"/></label>
    <input id="passwordId"
           type="password"
           name="password"/>
<%--    password end  --%>

<%--    LOGIN command--%>
    <input type="hidden" name="command" value="LOGIN"/>
    <button type="submit"
            class="btn btn-xs navbar-btn">
        <fmt:message key="header.link.sign_in.name"/>
    </button>
<%--    LOGIN command end--%>
</form>
<c:if test="${not empty sessionScope.loginValidationErrors}">
    <c:if test="${not sessionScope.loginValidationErrors.email}">
        <fmt:message key="page.sign.field.email.validation.message"/>
    </c:if>
</c:if>
<%@ include file="fragment/footer.jsp" %>
</body>
</html>
