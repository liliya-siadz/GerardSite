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
    <title><fmt:message key="page.admin_photos.title"/></title>
</head>
<body>
<%@ include file="fragment/headers/admin_header.jsp" %>

<form method="POST"
      action="${applicationPath}${controllerUrl}">
    <button type="submit"
            name="command"
            style="background-color: forestgreen"
            class="btn btn-lg"
            value="ADD_PHOTO">
        <b><fmt:message key="page.admin_photos.button.add_photo.title"/></b>
    </button>
</form>
<div style="display:inline-block;margin-left: 15%;">
    <c:set var="photos" value="${allPhotos}" scope="request"/>
    <c:forEach items="${photos}" var="photo">
    <h2><b><fmt:message key="page.admin_photos.view.title"/>
            #
    <c:out value="${photo.id}"/>
    </b></h2>
        <img class="img"
             src="${applicationPath}/${photo.photoPath}"
             alt="?"
             width="400px"/>
            <div>
                <div style="display:block;">
                    <h3><b><fmt:message key="page.admin_photos.view.title.photoPath"/> </b>
                        <c:out value="${photo.photoPath}"/>
                    </h3>
                </div>
                <div style="display:block;">
                    <h3><b><fmt:message key="page.admin_photos.view.title.name"/> </b>
                        <c:out value="${photo.name}"/>
                    </h3>
                </div>
                <div style="display:block;">
                    <h3><b><fmt:message key="page.admin_photos.view.title.photoDate"/> </b>
                        <c:out value="${photo.photoDate}"/>
                    </h3>
                </div>
                <div style="display:block;">
                    <h3><b><fmt:message key="page.admin_photos.view.title.id"/> </b>
                        <c:out value="${photo.id}"/>
                    </h3>
                </div>
                <div style="display: block" >
                <form method="POST"
                      action="${applicationPath}${controllerUrl}">
                        <button type="submit"
                            name="command"
                            style="background-color: orangered"
                            class="btn btn-md"
                            value="DELETE_PHOTO">
                       <b> <fmt:message key="page.admin_photos.button.delete_photo.title"/></b>
                        </button>
                    </form>
                 </div>
            </div>
        <hr style=" border: 1px dashed red">
        </c:forEach>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
