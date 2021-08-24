<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/12/2021
  Time: 9:30 PM
  To change this template use File | Settings | File Templates.
--%>
<fmt:message key="field.email.validation.message"
             var="loginValidationMessage"
             scope="application"/>
<c:set var="emailPattern" value="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
<c:set var="emailMaxLength" value="253"/>
<c:set var="emailMinLength" value="3"/>
<c:set var="emailPlaceholder" value="billy.joel@gmail.com"/>