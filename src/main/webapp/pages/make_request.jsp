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

    <c:set var="puppies" value="${allPuppies}" scope="request"/>
    <c:if test="${empty chosenPuppyDisplay}">
    <form method="GET"
          action="${applicationPath}${controllerUrl}">
        <select name="chosenPuppy">
            <c:forEach var="puppy" items="${puppies}">
                <option value="${puppy.id}">
                        ${puppy.fullname}
                </option>
            </c:forEach>
        </select>
        <button type="submit"
                name="command"
                class="btn btn-xs navbar-btn"
                value="DISPLAY_CHOSEN_PUPPY">
            <fmt:message key="page.make_request.button.choose.name"/>
        </button>
    </form>
    </c:if>
    <c:if test="${not empty chosenPuppyDisplay and empty chosenPuppy}">
        <c:set var="avatar" value="${chosenPuppyDisplay.avatarPhotoPath}"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.fullname"/> </b>
                    <c:out value="${chosenPuppyDisplay.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.birthday"/> </b>
                    <c:out value="${chosenPuppyDisplay.birthday}"/>
                </h3>
            </div>
        </div>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="512"/>
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
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
