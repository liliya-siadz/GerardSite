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
    <title><fmt:message key="page.make_request.title"/></title>
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
<%------------------base flow go: step 1
                chose puppy for request section--%>
<%@ include file="fragment/headers/header.jsp" %>
<c:set var="dogs" value="${allPuppies}" scope="session"/>
<c:if test="${empty chosenPuppy and not isRequestMade}">
    <form method="GET"
          action="${applicationPath}${controllerUrl}">
        <select name="chosenPuppyId">
            <c:forEach var="dog" items="${dogs}">
                <option value="${dog.id}">
                        ${dog.fullname}
                </option>
            </c:forEach>
        </select>
        <button type="submit"
                name="command"
                style="background-color: rebeccapurple"
                class="btn btn-xs navbar-btn"
                value="DISPLAY_CHOSEN_PUPPY">
            <fmt:message key="page.make_request.button.choose.name"/>
        </button>
    </form>
</c:if>
<%------------------base flow go: step 2(a)
                            display request form for chosen puppy--%>
<c:if test="${not empty chosenPuppy and not isRequestMade}">
    <h3 style="color: green">
        <b><fmt:message key="page.make_request.choose.result.text"/></b>
    </h3>
    <%@ include file="fragment/chosen_puppy.jsp" %>
    <form method="POST"
          action="${applicationPath}${controllerUrl}">
        <h3 style="color: maroon">
            <b><fmt:message key="page.make_request.request_form.label"/></b>
        </h3>
            <p><fmt:message key="field.content.label"/> (${contentValidationMessage})</p>
            <p><textarea name="content"
                         size="${contentMaxLength}"
                      required
                      pattern="${contentPattern}"
                      maxlength="${contentMaxLength}"
                      minlength="${contentMinLength}"
                      oninvalid="setCustomValidity('?')" >
            </textarea>
            </p>
        <p><fmt:message key="field.email.label"/>(${emailValidationMessage})</p>
           <p><input
                   name="email"
                   id="emailId"
                   required
                   size="${emailMaxLength}"
                   type="text"
                   placeholder="${emailPlaceholder}"
                   pattern="${emailPattern}"
                   maxlength="${emailMaxLength}"
                   minlength="${emailMinLength}"
                   oninvalid="setCustomValidity('?')"/>
           </p>
         <p><fmt:message key="field.name.label"/>(${nameValidationMessage})</p>
         <p><input
                 name="name"
                   type="text"
                   required
                   size="${nameMaxLength}"
                   pattern="${namePattern}"
                   maxlength="${nameMaxLength}"
                   minlength="${nameMinLength}"
                   oninvalid="setCustomValidity('?')"/>
        </p>
        <p><fmt:message key="field.surname.label"/>(${surnameValidationMessage})</p>
         <p><input
                 name="surname"
                   type="text"
                   required
                   size="${nameMaxLength}"
                   pattern="${namePattern}"
                   maxlength="${nameMaxLength}"
                   minlength="${nameMinLength}"
                   oninvalid="setCustomValidity('?')"/>
        </p>
         <p><fmt:message key="field.patronymic.label"/>(${patronymicValidationMessage})</p>
        <p><input
                name="patronymic"
                   type="text"
                   size="${nameMaxLength}"
                   pattern="${namePattern}"
                   maxlength="${nameMaxLength}"
                   minlength="${nameMinLength}"
                   oninvalid="setCustomValidity('?')"/>
        </p>

        <p><fmt:message key="field.phone.label"/>(${phoneValidationMessage})</p>
         <p> <input
                 name="phone"
                   type="text"
                   required
                   size="${strongLength}"
                   placeholder="${phonePlaceholder}"
                   pattern="${phonePattern}"
                   maxlength="${strongLength}"
                   minlength="${strongLength}"
                   oninvalid="setCustomValidity('?')"/>
         </p>
        <button type="submit"
                name="command"
                class="btn btn-xs navbar-btn"
                style="background-color: orangered"
                value="MAKE_REQUEST">
            <fmt:message key="page.make_request.button.make_request.name"/>
        </button>
    </form>
<%------------------base flow go: step 2(b)
                chose puppy again and go to step 1--%>
    <form method="GET"
          action="${applicationPath}${controllerUrl}">
        <button type="submit"
                name="command"
                class="btn btn-xs navbar-btn"
                style="background-color: rebeccapurple"
                value="GO_TO_MAKE_REQUEST_PAGE">
            <fmt:message key="page.make_request.button.look.name"/>
        </button>
    </form>

<%------after step 2(a) base flow go: step 3
                     (display server side validation result) --%>
<%--------- server-side validation erros-----%>
    <c:if test="${not empty sessionScope.requestValidationMap}">
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
    </c:if>
<%------------after step 3 base flow go: step 4
                    (display result of sent and validated request --%>
</c:if>
<c:if test="${not empty isRequestMade and isRequestMade}">
    <div style="display: block;text-align: center; margin-left:5%">
        <form method="POST"
              action="${applicationPath}${controllerUrl}">
            <button type="submit"
                    name="command"
                    style="background-color: forestgreen"
                    class="btn btn-md"
                    value="INVALIDATE_SESSION">
                <fmt:message key="isee.word"/>
            </button>
        </form>
        <hr>
    </div>
</c:if>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
