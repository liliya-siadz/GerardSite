<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"
         pageEncoding="UTF-8" language="java" %>
<%@ include file="fragment/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.login.title"/></title>
    <style>
        input:invalid {
            border: 2px dashed red;
        }
        input:valid {
            border: 2px solid black;
        }
    </style>
</head>
<body>
<%@include file="fragment/headers/header.jsp"%>
<c:if test="${not empty sessionScope.isUserFound}">
    <c:if test="${not sessionScope.isUserFound}">
        <p style="color: red"><fmt:message key="page.login.command.authorization.result"/></p>
    </c:if>
</c:if>
<form method="POST"
      action="${applicationPath}${controllerUrl}">
    <p><fmt:message key="field.email.label"/>:(${emailValidationMessage})</p>
    <p><input
           type="text"
           name="email"
           required
           size="${emailMaxLength}"
           placeholder="${emailPlaceholder}"
           pattern="${emailPattern}"
           maxlength="${emailMaxLength}"
           minlength="${emailMinLength}"
           oninvalid="setCustomValidity('?')"/>
    </p>
    <p><fmt:message key="field.password.label"/>:(${passwordValidationMessage}) </p>
    <p><input
           type="password"
           size="${passwordMaxLength}"
           required
           name="password"
           placeholder="${passwordPlaceholder}"
           pattern="${passwordPattern}"
           maxlength="${passwordMaxLength}"
           minlength="${passwordMinLength}"
           oninvalid="setCustomValidity('?')"/>
    </p>
    <button type="submit"
            name="command"
            value="LOGIN"
            class="btn btn-xs btn-outline-danger navbar-btn">
        <fmt:message key="page.login.button.login.name"/>
    </button>
</form>
<%--------------------        server-side validation erros---------------------%>
<c:if test="${not empty sessionScope.loginValidationMap}">
             <c:if test="${not sessionScope.loginValidationMap.email}">
                 &#9888;<b>   <fmt:message key="field.email.label"/> </b>&#9746;
                 :
                 <p style="color:red;"><fmt:message key="field.email.validation.message"/></p>
            </c:if>
             <c:if test="${not sessionScope.loginValidationMap.password}">
                 &#9888;<b><fmt:message key="field.password.label"/></b>&#9746;
                  :
                 <p style="color:red;"><fmt:message key="field.password.validation.message"/></p>
            </c:if>
</c:if>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
