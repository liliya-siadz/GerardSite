<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/12/2021
  Time: 9:30 PM
  To change this template use File | Settings | File Templates.
--%>
<fmt:message key="field.email.validation.message"
             var="emailValidationMessage"
             scope="application"/>
<c:set var="emailPattern" value="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
<c:set var="emailMaxLength" value="253"/>
<c:set var="emailMinLength" value="3"/>
<c:set var="emailPlaceholder" value="billy.joel@gmail.com"/>

<fmt:message key="field.password.validation.message"
             var="passwordValidationMessage"
             scope="application"/>
<c:set var="passwordPattern" value="[\\w]*"/>
<c:set var="passwordMaxLength" value="15"/>
<c:set var="passwordMinLength" value="6"/>
<c:set var="passwordPlaceholder" value="alexander142"/>

<fmt:message key="field.name.validation.message"
             var="nameValidationMessage"
             scope="application"/>
<c:set var="namePattern" value="[a-zA-Z\u0400-\u04ff]{3,250}"/>
<c:set var="nameMaxLength" value="250"/>
<c:set var="nameMinLength" value="3"/>

<fmt:message key="field.surname.validation.message"
             var="surnameValidationMessage"
             scope="application"/>

<fmt:message key="field.patronymic.validation.message"
             var="patronymicValidationMessage"
             scope="application"/>


<fmt:message key="field.content.validation.message"
             var="contentValidationMessage"
             scope="application"/>
<c:set var="contentPattern" value="[\\w!.?,:\s]*"/>
<c:set var="contentMaxLength" value="450"/>
<c:set var="contentMinLength" value="10"/>

<fmt:message key="field.phone.validation.message"
             var="phoneValidationMessage"
             scope="application"/>
<c:set var="phonePattern" value="^[25|44|33|29]\\d*"/>
<c:set var="strongLength" value="9"/>
<c:set var="phonePlaceholder" value="293060661"/>
