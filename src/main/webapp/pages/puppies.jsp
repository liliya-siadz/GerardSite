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
    <title><fmt:message key="page.puppies.title"/></title>
</head>
<body>
<%@include file="fragment/headers/header.jsp" %>
<div style="display:inline-block;">
    <c:set var="puppiesForView" value="${puppies}" scope="session"/>
    <c:forEach items="${puppiesForView}" var="puppyForView">
        <c:set var="avatar" value="${puppyForView.avatarPhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="512"/>
        <c:set var="pedigree" value="${puppyForView.pedigreePhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${pedigree}"
             alt="?"
             width="512"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.sex"/> </b>
                    <c:out value="${puppyForView.dogSex}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.nickname"/> </b>
                    <c:out value="${puppyForView.nickname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.fullname"/> </b>
                    <c:out value="${puppyForView.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.birthday"/> </b>
                    <c:out value="${puppyForView.birthday}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.description"/> </b>
                    <c:out value="${puppyForView.description}"/><c:if test="${empty puppyForView.description}">&#10006;</c:if>
                </h3>
            </div>
            <form method="GET"
                  action="${applicationPath}${controllerUrl}">
                <input type="hidden" value="${puppyForView.id}" name="chosenPuppyId"/>
                <button type="submit"
                        name="command"
                        class="btn btn-lg btn-dark"
                        style="background-color: red"
                        value="CHOSE_PUPPY">
                        <fmt:message key="page.puppies.button.make_request.name"/>
            </form>
        </div>
    </c:forEach>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
