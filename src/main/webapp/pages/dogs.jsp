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
    <title><fmt:message key="page.dogs.title"/></title>
</head>
<body>
<%@include file="fragment/headers/header.jsp" %>
<div style="display:inline-block;">
    <c:set var="dogsForView" value="${dogsForView}" scope="request"/>
    <c:forEach items="${dogsForView}" var="dogForView">
        <c:set var="avatar" value="${dogForView.avatarPhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="512"/>
        <c:set var="pedigree" value="${dogForView.pedigreePhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${pedigree}"
             alt="?"
             width="512"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.sex"/> </b>
                    <c:out value="${dogForView.dogSex}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.nickname"/> </b>
                    <c:out value="${dogForView.nickname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.fullname"/> </b>
                    <c:out value="${dogForView.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogEntities.view.title.birthday"/> </b>
                    <c:out value="${dogForView.birthday}"/>
                </h3>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
