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
<c:set var="emailPattern" value="my.email@gmail.com"/>
<c:set var="emailMaxLength" value="5"/>
<c:set var="emailMinLength" value="3"/>
<c:set var="emailPlaceholder" value="billy.joel@gmail.com"/>

<%--а) валидация на стороне клиента--%>
<%--б) валидация на стороне сервера--%>
<%--СТРОКОВЫЕ ЗНАЧЕНИЯ--%>
<%--1 макс длина--%>
<%--2 мин длина--%>
<%--3 regex--%>

<%--ЧИСЛОВЫЕ ЗНАЧЕНИЯ--%>
<%--0 длина поля--%>
<%--1 максимальное значение--%>
<%--2 минимальное значение--%>
<%--3 может ли быть  blank/null--%>