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
<html>
    <head></head>
    <body>
    </body>
</html>
