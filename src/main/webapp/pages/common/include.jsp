<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/4/2021
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ilab" uri="init-locale-and-bundle" %>
<%@ page session="true" %>
<ilab:init-locale-and-bundle/>
<fmt:setBundle basename="${sessionScope.bundle}"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<c:set var="applicationPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="servletUrl" value="${initParam.servlet_url}" scope="application"/>
<html dir="ltr">
    <head>
        <link rel="icon" type="image/x-icon" href="${applicationPath}/favicon.ico">
        <link rel="stylesheet" href="${applicationPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${applicationPath}/css/bootstrap-theme.min.css">
        <meta name="description" content="Kennel presentation for clients"/>
        <meta name="keywords" content="dog, leonberger, Gerard, leon, kennel, собака, питомник, Жерард, леонбергер,сабака, пітомнік, Жерард, леонбергер"/>
        <meta name="author" content="Liliya Siadzelnikava"/>
    </head>
    <body>
      <div class="container">
        <div class="row">
          <div class="col-md-2" style="background-color:blue"> left </div>
          <div class="col-md-2" style="background-color:green"> middle </div>
          <div class="col-md-8" style="background-color:black"> right </div>
        </div>
      </div>
    <form method="post"
          action="${applicationPath}${servletUrl}">
        <button type="submit"
                name="command"
                value="SWITCH_LOCALE_TO_EN">
            EN
        </button>
        <button type="submit"
                name="command"
                value="SWITCH_LOCALE_TO_BE">
            BE
        </button>
        <button type="submit"
                name="command"
                value="SWITCH_LOCALE_TO_RU">
            RU
        </button>
    </form>
        <a href="home">home</a>
        <a href="dogs">dogs</a>
        <script src="${applicationPath}/js/jquery-3.1.0.min.js"></script>
        <script src="${applicationPath}/js/bootstrap.min.js"></script>
    </body>
</html>
