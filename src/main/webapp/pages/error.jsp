<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"
         pageEncoding="UTF-8" language="java" %>
<%@ include file="fragment/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.error.title"/></title>
    <style>
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
<h1 style="text-align: center">
    <b><fmt:message key="error.word"/> !
        <fmt:message key="page.error.message.alternative_action"/>
    </b>
    &#9758;
    <button class="btn btn-md" onclick="history.back()">
        <fmt:message key="getback.word"/>
    </button>
</h1>
<h1><fmt:message key="page.error.message.signal"/></h1>
<div style="display: inline">
    <div class="customAnimation"></div>
    <div style="display: inline-block">
        <div><h3><fmt:message key="page.error.message.fun"/></h3></div>
        <div>
            <h3><b><fmt:message key="page.error.message.action"/></b>
                <a href="${pageContext.request.contextPath}/home">
                    &#8962;
                    <fmt:message key="page.home.header"/>
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
