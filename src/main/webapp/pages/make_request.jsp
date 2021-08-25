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
<%@ include file="fragment/headers/header.jsp" %>
<div style="margin-left: 30%;display: block">
    <c:set var="dogs" value="${allPuppies}" scope="session"/>
    <c:if test="${empty chosenPuppy and not isPuppyChosen}">
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
                    style="background-color: deepskyblue"
                    class="btn btn-xs navbar-btn"
                    value="CHOSE_PUPPY">
                <fmt:message key="page.make_request.button.choose.name"/>
            </button>
        </form>
    </c:if>
    <c:if test="${not empty chosenPuppy and isPuppyChosen and not isRequestMade}">
        <h3 style="color: green">
            <b><fmt:message key="page.make_request.choose.result.text"/></b>
        </h3>
        <%@ include file="fragment/forms/chosen_puppy_display.jsp" %>
        <%@ include file="fragment/forms/request_form.jsp" %>
        <form method="GET"
              action="${applicationPath}${controllerUrl}">
            <button type="submit"
                    name="command"
                    class="btn btn-xs navbar-btn"
                    value="GO_TO_MAKE_REQUEST_PAGE">
                <fmt:message key="page.make_request.button.look.name"/>
            </button>
        </form>
    </c:if>
    <c:if test="${isRequestMade}">
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
    <c:if test="${not empty requestValidationMap and not isRequestMade}">
        <%@ include file="fragment/validation_maps/request_validation_map.jsp" %>
    </c:if>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
