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
</head>
<body>
<%@ include file="fragment/headers/header.jsp" %>
<div style="margin-left: 30%;display: block">
    <c:set var="puppies" value="${puppies}" scope="session"/>
    <c:if test="${empty chosenPuppy and not isPuppyChosen}">
        <form method="GET"
              action="${applicationPath}${controllerUrl}">
            <select name="chosenPuppyId">
                <c:forEach var="puppy" items="${puppies}">
                    <option value="${puppy.id}">
                            ${puppy.fullname} : ${puppy.dogSex} : ${puppy.birthday}
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
        <p><fmt:message key="page.make_request.prechose.text1"/></p>
        <p><fmt:message key="page.make_request.prechose.text2"/></p>
        <p><fmt:message key="page.make_request.prechose.text3"/>
         &#8595;
        <form method="GET"
              action="${applicationPath}${controllerUrl}"
              style="margin-left: 40%">
            <button type="submit"
                    name="command"
                    class="btn btn-lg btn-dark headerButton"
                    style="border: 1px solid black;"
                    value="GO_TO_PUPPIES_PAGE">
                <fmt:message key="page.puppies.header"/>
            </button>
        </form>
    </c:if>
    <c:if test="${not empty chosenPuppy and isPuppyChosen and not isRequestMade}">
        <h3 style="color: green">
            <b><fmt:message key="page.make_request.choose.result.text"/></b>
        </h3>
        <%@ include file="fragment/chosen_puppy_display.jsp" %>
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
        <fmt:message key="page.dogEntities.view.title.sex" var="sex"/>
        <fmt:message key="page.dogEntities.view.title.nickname" var="nickname"/>
        <fmt:message key="page.dogEntities.view.title.fullname" var="fullname"/>
        <fmt:message key="page.dogEntities.view.title.birthday" var="birthday"/>
        <fmt:message key="page.maker_request.view_result.text1" var="text1"/>
        <fmt:message key="page.maker_request.view_result.text2" var="text2"/>
        <fmt:message key="page.maker_request.view_result.text3" var="text3"/>
        <fmt:message key="page.maker_request.view_result.text4" var="text4"/>
        <script>
            confirm('${text1}'
                + '\n ${sex}${chosenPuppy.dogSex}'
                + '\n ${nickname}${chosenPuppy.nickname}'
                + '\n ${fullname}${chosenPuppy.fullname}'
                + '\n ${birthday}${chosenPuppy.birthday}'
                + '\n ${text2}\n ${email}'
                + '\n ${text3}: ${phone}'
                + '\n ${text4}\n gerard.kennel@inbox.ru')
            sessionStorage.clear();
            window.location.href = "home";
        </script>
    </c:if>
    <c:if test="${not empty requestValidationMap and not isRequestMade}">
        <%@ include file="fragment/validation_maps/request_validation_map.jsp" %>
    </c:if>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
