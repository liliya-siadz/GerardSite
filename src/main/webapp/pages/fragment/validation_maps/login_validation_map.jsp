<c:if test="${not empty loginValidationMap}">
    <c:if test="${not loginValidationMap.email}">
          &#9888;<b>   <fmt:message key="field.email.label"/> </b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.email.validation.message"/></p>
    </c:if>
    <c:if test="${not loginValidationMap.password}">
        &#9888;<b><fmt:message key="field.password.label"/></b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.password.validation.message"/></p>
    </c:if>
</c:if>