<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"
         pageEncoding="UTF-8" language="java" %>
<%@ include file="fragment/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.error-404.title"/></title>
    <style>
        div.customAnimation {
            width: 100px;
            height: 100px;
            background-color: red;
            position: relative;
            animation: custom_animation 10s linear 0s infinite alternate;
        }

        @keyframes custom_animation {
            0% {
                background-color: red;
                left: 0px;
                top: 0px;
            }
            25% {
                background-color: yellow;
                left: 200px;
                top: 0px;
            }
            50% {
                background-color: blue;
                left: 200px;
                top: 200px;
            }
            75% {
                background-color: green;
                left: 0px;
                top: 200px;
            }
            100% {
                background-color: red;
                left: 0px;
                top: 0px;
            }
        }
    </style>
</head>
<body>
<%@include file="fragment/dynamic-headering.jsp" %>
<h1 style="text-align: center">
    <b><fmt:message key="error.word"/>${pageContext.errorData.statusCode} !
    <fmt:message key="page.error.message.alternative_action"/></b>
    &#9758;
    <button class="btn btn-md" onclick="history.back()">
        <fmt:message key="getback.word"/>
    </button>
</h1>
<div style="display: inline">
    <div class="customAnimation"></div>
    <div style="display: inline-block">
        <div>
            <h3><fmt:message key="page.error-404.message.signal"/>
                <span style="background: yellowgreen">
                     ${header.host}${pageContext.errorData.requestURI}
                </span>
            </h3>
        </div>
        <div><h3><fmt:message key="page.error.message.fun"/></h3></div>
        <div>
            <h3><b><fmt:message key="page.error.message.action"/></b>
                <a href="${pageContext.request.contextPath}/home">
                    &#8962;
                    <fmt:message key="header.link.home.name"/>
                </a>
            </h3>
        </div>
    </div>
    <img class="img-fluid"
         src="${applicationPath}/img/error.png"
         alt="GERARD"
         width="512"/>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
