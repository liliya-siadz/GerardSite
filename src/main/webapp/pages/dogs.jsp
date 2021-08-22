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
<body style="background-image: url(${applicationPath}/img/background.jpg);">
<%@include file="fragment/dynamic-headering.jsp" %>

<div style="display:inline-block;">
    <c:set var="puppies" value="${allDogs}" scope="request"/>
    <c:forEach items="${puppies}" var="puppy">
        <c:set var="avatar" value="${puppy.avatarPhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="512"/>
        <c:set var="pedigree" value="${puppy.pedigreePhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${pedigree}"
             alt="?"
             width="512"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.sex"/> </b>
                    <c:out value="${puppy.dogSex}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.nickname"/> </b>
                    <c:out value="${puppy.nickname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.fullname"/> </b>
                    <c:out value="${puppy.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.birthday"/> </b>
                    <c:out value="${puppy.birthday}"/>
                </h3>
            </div>
        </div>
    </c:forEach>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
