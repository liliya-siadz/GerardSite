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
<%@include file="fragment/headers/header.jsp" %>
    <c:if test="${not empty photosForView}">
<%--        single photo displaying start--%>
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
            </div>
        </c:forEach>
<%----------------------------------------------------single photo displaying end--%>
<%--        page navigation start--%>
        <c:choose>
            <c:when test="${not empty photosForView}">
        <div class="pages">
                    <ul class="pagination">
                        <li>
                            <a class="page-link ${pagination_item.isFirstPage() ? 'disabled' : ''}"
                               href="${applicationPath}${controllerUrl}?command=GO_TO_PHOTOS_PAGE&page=${pagination_item.currentPage - 1}">
                                &#8592;
                            </a>
                        </li>
                        <c:forEach var="i" begin="1" end="${pagination_item.pageCount()}">
                            <li class="${pagination_item.currentPage eq i ? 'active': ''}">
                                <a class="page-link" href="${applicationPath}${controllerUrl}?command=GO_TO_PHOTOS_PAGE&page=${i}">
                                        ${i}
                                </a>
                            </li>
                        </c:forEach>
                        <li>
                            <a class="page-link ${pagination_item.isLastPage() ? 'disabled' : ''}"
                               href="${applicationPath}${controllerUrl}?command=GO_TO_PHOTOS_PAGE&page=${pagination_item.currentPage + 1}">
                                &#8594;
                            </a>
                        </li>
                    </ul>
            </c:when>
            <c:otherwise>
                &#10006;
            </c:otherwise>
        </c:choose>
        </div>
<%--------------------------------page navigation end--%>
    </c:if>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
