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
<%--footer start--%>
<div class="container-fluid panel-footer" style="{ font-size: 5px; }">
    <div class="row">
        <small><div class="col-md-1"><a href="home"><fmt:message key="footer.link.home.name"/></a></div></small>
        <small>
            <div class="col-md-3">
                <fmt:message key="footer.link.coordinates.name"/>
                : &#9737;
                <b><osd:output-site-description applicationUrl="${initParam.application_url}"
                        elementTagName="cordinates" /></b>
                &#9200;
                <c:out value="${currentDate}"/>
            </div>
        </small>
        <small>
            <div class="col-md-3">
            <fmt:message key="footer.link.address.name"/>
                : &#9749;
            <b><fmt:message key="kennel.address"/></b>
            </div>
        </small>
        <small>
            <div class="col-md-2">
            <fmt:message key="footer.link.phone.name"/>
                : &#9742;
            <b><osd:output-site-description applicationUrl="${initParam.application_url}" elementTagName="phone" /></b>
            </div>
        </small>
        <small>
            <div class="col-md-3">
            <fmt:message key="footer.link.email.name"/>
                : &#9755;
            <b><osd:output-site-description applicationUrl="${initParam.application_url}" elementTagName="email" /></b>
            </div>
        </small>
    </div>
</div>
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
</html>
