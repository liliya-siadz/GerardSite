
    <c:if test="${not sessionScope.requestValidationMap.content}">
        &#9888;<b> <fmt:message key="field.content.label"/> </b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.content.validation.message"/></p>
    </c:if>
    <c:if test="${not sessionScope.requestValidationMap.email}">
        &#9888;<b> <fmt:message key="field.email.label"/> </b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.email.validation.message"/></p>
    </c:if>
    <c:if test="${not sessionScope.requestValidationMap.name}">
        &#9888;<b> <fmt:message key="field.name.label"/> </b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.name.validation.message"/></p>
    </c:if>
    <c:if test="${not sessionScope.requestValidationMap.surname}">
        &#9888;<b> <fmt:message key="field.surname.label"/> </b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.name.validation.message"/></p>
    </c:if>
    <c:if test="${not sessionScope.requestValidationMap.patronymic}">
        &#9888;<b> <fmt:message key="field.patronymic.label"/> </b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.name.validation.message"/></p>
    </c:if>
    <c:if test="${not sessionScope.requestValidationMap.phone}">
        &#9888;<b> <fmt:message key="field.phone.label"/> </b>&#9746;
        :
        <p style="color:red;"><fmt:message key="field.phone.validation.message"/></p>
    </c:if>