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
    <title><fmt:message key="page.admin_dogs.title"/></title>
</head>
<body>


<%@ include file="fragment/headers/admin_header.jsp" %>

<div style="display:inline-block;">
    <c:set var="dogs" value="${allDogs}" scope="request"/>
    <c:forEach items="${dogs}" var="dog">
        <c:set var="avatar" value="${dog.avatarPhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="512"/>
        <c:set var="pedigree" value="${dog.pedigreePhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${pedigree}"
             alt="?"
             width="512"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.admin_dogs.title.sex"/> </b>
                    <c:out value="${dog.dogSex}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.admin_dogs.title.is_active"/> </b>

                    <c:choose>
                        <c:when test="${dog.active eq true}">
                            &#9971;</c:when>
                        <c:otherwise>
                            &#9940;
                        </c:otherwise>
                    </c:choose>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.admin_dogs.title.birthday"/> </b>
                    <c:out value="${dog.birthday}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_dogs.view.title.nickname"/> </b>
                    <c:out value="${dog.nickname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_dogs.view.title.fullname"/> </b>
                    <c:out value="${dog.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.admin_dogs.title.description"/> </b>
                    <c:out value="${dog.description}"/>
                </h3>
            </div>
        </div>
        <hr style=" border: 1px dashed red">
    </c:forEach>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
