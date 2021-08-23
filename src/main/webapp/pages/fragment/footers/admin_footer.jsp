<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/7/2021
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head></head>
<body>
    <%-- current place description start--%>
<div class="container">
    <div class="col-md-2"><small><b>application path:</b>${applicationPath}</small></div>
    <div class="col-md-2"><small><b>controller url:</b> ${controllerUrl}</small></div>
    <div class="col-md-2"><small><b>uri:</b> ${pageContext.request.requestURI}</small></div>
    <div class="col-md-3"><small><b>relative to controller url location:</b>${pageContext.request.servletPath}</small></div>
    <div class="col-md-3"><small><b>full url from web.xml:</b> ${pageContext.request.requestURL}</small></div>
</div>
    <%--current place description end----%>
<%--footer end----%>
</body>
<%@ include file="footer.jsp" %>
</html>
