<html>
<head>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      <%@ include file="../logo.jsp" %>
      <li><a href="${pageContext.request.contextPath}/home"><fmt:message key="header.link.home.name"/></a></li>
      <li><a href="${pageContext.request.contextPath}/dogs"><fmt:message key="header.link.dogs.name"/></a></li>
      <li><a href="${pageContext.request.contextPath}/puppies"><fmt:message key="header.link.puppies.name"/></a></li>
      <li><a href="${pageContext.request.contextPath}/photos"><fmt:message key="header.link.photos.name"/></a></li>
      <li><a style="color: #8a6d3b" href="${pageContext.request.contextPath}/admin/admin_dogs"><fmt:message key="header.link.admin_dogs.name"/></a></li>
      <li><a style="color: #8a6d3b" href="${pageContext.request.contextPath}/admin/admin_photos"><fmt:message key="header.link.admin_photos.name"/></a></li>
      <li><a style="color: #8a6d3b" href="${pageContext.request.contextPath}/admin/users"><fmt:message key="header.link.users.name"/></a></li>
      <li><a style="color: #8a6d3b" href="${pageContext.request.contextPath}/admin/requests"><fmt:message key="header.link.requests.name"/></a></li>
      <li><a style="color: #8a6d3b" href="${pageContext.request.contextPath}/authaccessed/my_profile"><fmt:message key="header.link.my_profile.name"/></a></li>
    </ul>
    <%@ include file="../sign_out_button.jsp" %>
    <%@ include file="../switch_locale_panel.jsp" %>
  </div>
</nav>
</body>
</html>
