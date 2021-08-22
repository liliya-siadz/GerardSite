<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="fragment/include.jsp" %>
<html>
<head>
    <title><fmt:message key="page.photos.title"/></title>
</head>
<body>
<%@include file="fragment/dynamic-headering.jsp"%>
    <c:set var="photos" value="${allPhotosWithDogsName}" scope="request"/>
    <c:forEach items="${photos}" var="photo">
        <div style="display: block;">
        <c:if test="${not empty photo.nickname}">
            <h3><b style="margin-left: 15%"><c:out value="${photo.nickname} : ${photo.photoDate}"/></b></h3>
        </c:if>
        <img class="img"
             src="${applicationPath}/${photo.photoPath}"
             alt="?"
             style="text-align: center; margin-left:15%"
             width="400px"/>
    </c:forEach>
        </div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
