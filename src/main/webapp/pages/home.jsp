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
    <title><fmt:message key="page.home.title"/></title>
    </head>
    <body style="background-image: url(${applicationPath}/img/background.jpg);">
    <%@include file="fragment/dynamic-headering.jsp"%>
    <c:choose>
        <c:when test="${admin}">
            <img src="${applicationPath}/img/admin.png"
                 alt="$"
                 width="150"
                 style="margin-top:5px;margin-right:60px">
            <h1>
                &#8986;<fmt:formatDate type="time" value="${date}"/>
            </h1>
            <fmt:message key="page.home.admin_welcome"/>
            <%@ include file="fragment/footers/admin_footer.jsp" %>
        </c:when>
        <c:otherwise>
            <div style="display: inline-block">
                <div style="width: 400px; text-align: center" >
                    <p><b>
                        <fmt:message key="page.home.presentation.text.part1"/>
                    </b>
                    </p>
                    <p>
                        <fmt:message key="page.home.presentation.text.part2"/>
                    </p>
                    <p>
                        <fmt:message key="page.home.presentation.text.part3"/>
                    </p>
                    <p><i>
                        <fmt:message key="page.home.presentation.text.part4"/>
                    </i>
                    </p>
                </div>
                <img class="img-fluid"
                     src="${applicationPath}/img/home.jpg"
                     alt="GERARD"
                     width="200"/>
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2351.796632288363!2d27.436444215377048!3d53.88204334215154!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbdae622536735%3A0x3a57a54c60001109!2zdnVsaWNhIMWgYXJhbmhvdmnEjWEsIE1pbnNr!5e0!3m2!1sen!2sby!4v1629635357257!5m2!1sen!2sby" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
            </div>
            <%@ include file="fragment/footers/footer.jsp" %>
        </c:otherwise>
    </c:choose>
    </body>
</html>
