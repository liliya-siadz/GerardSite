<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/12/2021
  Time: 9:30 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<body>


<fmt:message key="page.sign.field.email.validation.message"
             var="loginValidationMessage"
             scope="application"/>
<c:set var="emailPattern" value="my.email@gmail.com"/>
<c:set var="emailMaxLength" value="5"/>
<c:set var="emailMinLength" value="3"/>
<c:set var="emailPlaceholder" value="billy.joel@gmail.com"/>
</body>
</html>
