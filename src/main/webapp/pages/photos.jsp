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
    <title><fmt:message key="page.photos.title"/></title>
</head>
<body>
<%@include file="fragment/dynamic-headering.jsp"%>
    <c:set var="photosForView" value="${photosForView}" scope="session"/>
    <c:forEach items="${photosForView}" var="photoForView">
        <div style="display: block;">
        <c:if test="${not empty photoForView.nickname}">
            <h3><b style="margin-left: 15%"><c:out value="${photoForView.nickname} : ${photoForView.photoDate}"/></b></h3>
        </c:if>
        <img class="img"
             src="${applicationPath}/${photoForView.photoPath}"
             alt="?"
             style="text-align: center; margin-left:15%"
             width="400px"/>
    </c:forEach>
        </div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
