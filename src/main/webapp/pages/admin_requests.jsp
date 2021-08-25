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
    <title><fmt:message key="page.admin_requests.title"/></title>
</head>
<body>
<%@ include file="fragment/headers/admin_header.jsp" %>
<div style="display:inline-block;margin-left: 15%;">
    <br>
    <c:set var="requestsInfo" value="${allRequests}" scope="request"/>
    <c:forEach items="${requestsInfo}" var="requestInfo">
        <c:set var="avatar" value="${requestInfo.avatarPhotoPath}"/>
        <h2><b><fmt:message key="page.admin_requests.view.title"/>
             #
            <c:out value="${requestInfo.requestId}"/></b></h2>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="512"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.requestStatus"/> </b>
                    <c:out value="${requestInfo.requestStatus}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.requestType"/> </b>
                    <c:out value="${requestInfo.requestType}"/>
                </h3>
            </div>
            <br>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.content"/> </b>
                    <c:out value="${requestInfo.content}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.dateFact"/> </b>
                    <c:out value="${requestInfo.dateFact}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.email"/> </b>
                    <c:out value="${requestInfo.email}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.name"/> </b>
                    <c:out value="${requestInfo.name}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.surname"/> </b>
                    <c:out value="${requestInfo.surname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.phone"/> </b>
                    <c:out value="${requestInfo.phone}"/>
                </h3>
            </div>
            <br>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.nickname"/> </b>
                    <c:out value="${requestInfo.nickname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.birthday"/> </b>
                    <c:out value="${requestInfo.birthday}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.fullname"/> </b>
                    <c:out value="${requestInfo.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.dogSex"/> </b>
                    <c:out value="${requestInfo.dogSex}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.reply"/> </b>
                    <c:out value="${requestInfo.reply}"/>
                </h3>
            </div>
        </div>
        <br>
        <hr style=" border: 1px dashed red">
    </c:forEach>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
