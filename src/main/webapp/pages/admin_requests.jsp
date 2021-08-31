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
<c:set var="requestsForAdmin" value="${requestsForAdmin}" scope="session"/>
<c:forEach items="${requestsForAdmin}" var="requestForAdmin">
<c:set var="itemStyle" value="${requestForAdmin.requestStatus eq 'PENDING' ? 'pendingRequest' : 'notPendingRequest'}"/>
<hr>
    <div style="display:inline-block;margin-left: 15%;text-align: center"
     class="${itemStyle}">
    <br>
        <c:set var="avatar" value="${requestForAdmin.avatarPhotoPath}"/>
        <h2><b><fmt:message key="page.admin_requests.view.title"/>
             #
            <c:out value="${requestForAdmin.requestId}"/></b></h2>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="200"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.requestStatus"/> </b>
                    <c:out value="${requestForAdmin.requestStatus}"/>
                </h3>
            </div>
            <br>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.content"/> </b>
                    <c:out value="${requestForAdmin.content}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.dateFact"/> </b>
                    <c:out value="${requestForAdmin.dateFact}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.email"/></b>
                    <c:out value="${requestForAdmin.email}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.name"/> </b>
                    <c:out value="${requestForAdmin.name}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.surname"/> </b>
                    <c:out value="${requestForAdmin.surname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.phone"/> </b>
                    <c:out value="${requestForAdmin.phone}"/>
                </h3>
            </div>
            <br>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.nickname"/> </b>
                    <c:out value="${requestForAdmin.nickname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.birthday"/> </b>
                    <c:out value="${requestForAdmin.birthday}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.fullname"/> </b>
                    <c:out value="${requestForAdmin.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.admin_requests.view.title.dogSex"/> </b>
                    <c:out value="${requestForAdmin.dogSex}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3 style="width:50%"><b style="color: greenyellow">
                    <fmt:message key="page.admin_requests.view.title.reply"/> </b>
                    <c:out value="${requestForAdmin.reply}"/>
                </h3>
            </div>
        </div>
        <br>
        <form method="POST"
              action="${applicationPath}${controllerUrl}">
            <input type="hidden" value="${requestForAdmin.requestId}" name="requestId"/>
            <button type="submit"
                    name="command"
                    onClick="window.location.reload();"
                    class="btn btn-lg btn-success"
                    value="ACCEPT_REQUEST">
                &#10004;
            </button>
            <button type="submit"
                    name="command"
                    onClick="window.location.reload();"
                    class="btn btn-lg btn-danger"
                    value="REJECT_REQUEST">
                &#10006;
            </button>
        </form>
</div>
</c:forEach>
<%@ include file="fragment/footers/admin_footer.jsp" %>
</body>
</html>
