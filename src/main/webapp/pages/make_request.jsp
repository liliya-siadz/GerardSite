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
</head>
<body>
<%--+ ADD button  1 MAKE_REQUEST--%>
<%@ include file="fragment/headers/header.jsp" %>
    <c:set var="puppies" value="${allPuppies}" scope="session"/>
    <c:if test="${empty chosenPuppy and not isRequestMade}">
    <form method="GET"
          action="${applicationPath}${controllerUrl}">
        <select name="chosenPuppyId">
            <c:forEach var="puppy" items="${puppies}">
                <option value="${puppy.id}">
                        ${puppy.fullname}
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
    <c:if test="${not empty chosenPuppy and not isRequestMade}">
<%--        text notification that is sending email to:--%>
        <form method="POST"
              action="${applicationPath}${controllerUrl}">
                    <h3>
                        <b><fmt:message key="page.make_request.request_form.label"/></b>
                    </h3>
            <label for="content">
                <fmt:message key="field.content.label"/>
                <textarea value="content"> </textarea>
            </label>
            <label for="email">
                <fmt:message key="field.email.label"/>
                <input value="email" name="email"/>
            </label>
            <label for="name">
                <fmt:message key="field.name.label"/>
                <input value="name" name="name"/>
            </label>
            <label for="surname">
                <fmt:message key="field.surname.label"/>
                <input value="surname" name="surname"/>
            </label>
            <label for="patronymic">
                <fmt:message key="field.patronymic.label"/>
                <input value="patronymic" name="patronymic"/>
            </label>
            <label for="phone">
                <fmt:message key="field.phone.label"/>
                <input value="phone" name="phone"/>
            </label>
            <button type="submit"
                    name="command"
                    class="btn btn-xs navbar-btn"
                    style="background-color: orangered"
                    value="MAKE_REQUEST">
                <fmt:message key="page.make_request.button.make_request.name"/>
            </button>
        </form>
        <%@ include file="fragment/chosen_puppy.jsp" %>
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
    </c:if>
    <c:if test="${not empty isRequestMade and isRequestMade}">
        <div style="display: block;text-align: center; margin-left:5%" >
<%--          display response app user + request +dog--%>
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
<%--            <%@include file="fragment/chosen_puppy.jsp"%>--%>
        </div>
    </c:if>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
