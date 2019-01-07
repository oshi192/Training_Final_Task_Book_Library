<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
    <head>
        <title>Login page</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/login5.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/bootstrap/js/jquery-3.2.1.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
    </head>
    <body>
    <div class="container-fluid bg">
    <jsp:include page="parts/navbar.jsp"/>
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">

                <div id="log">
                    <form  method="POST" action="${pageContext.request.contextPath}/registration">
                        <h1>${msg:getMessage("registration-please-register")}</h1>
                        <div id="errormessagesbox">
                            <c:if test='${errorMessage!=""}'>
                                <c:out value="${errorMessage}"/>
                            </c:if>
                        </div>
                        <div id="messagesbox">
                            <c:if test='${message!=""}'>
                                <c:out value="${message}"/>
                            </c:if>
                        </div>
                        <img class="rounded mx-auto d-block img img-responsive img-circle" src="<c:url value="/resources/image/user1r.png" />"/>
                            <div class="form-group">
                                ${requestScope.messages.email}
                            <div>
                            <div class="form-group">
                                <input
                                    name="email"
                                    type="email"
                                    class="form-control"
                                    placeholder=${msg:getMessage("login-label-email")}
                                    pattern="${msg:getMessage("regex-email")}"
                                    title="${msg:getMessage("hint-email")}"
                                    value="${requestScope.newUser.getEmail()}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.phoneNumber}
                            <div>
                            <div class="form-group">
                                <input
                                    name="phone-number"
                                    type="text"
                                    class="form-control"
                                    placeholder=${msg:getMessage("registration-label-phone-number")}
                                    pattern="${msg:getMessage("regex-phone")}"
                                    title="${msg:getMessage("hint-phone")}"
                                    value="${requestScope.newUser.getPhoneNumber()}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.password}
                            <div>
                            <div class="form-group">
                                <input
                                    name="password"
                                    type="password"
                                    class="form-control"
                                    placeholder=${msg:getMessage("registration-label-password")}
                                    pattern="${msg:getMessage("regex-password")}"
                                    title="${msg:getMessage("hint-password")}"
                                    value="${requestScope.newUser.getPassword()}"
                                    required>
                            </div>
                            <div class="form-group">
                                <input
                                    name="confirm-password"
                                    type="password"
                                    class="form-control"
                                    placeholder=${msg:getMessage("registration-label-confirm-password")}
                                    pattern="${msg:getMessage("regex-password")}"
                                    title="${msg:getMessage("hint-password")}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.name}
                            <div>
                            <div class="form-group">
                                <input
                                    name="first-name"
                                    type="text"
                                    placeholder=${msg:getMessage("registration-label-first-name")}
                                    class="form-control"
                                    pattern="${msg:getMessage("regex-name")}"
                                    title="${msg:getMessage("hint-name")}"
                                    value="${requestScope.newUser.getName()}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.surname}
                            <div>
                            <div class="form-group">
                                <input
                                    name="surname"
                                    type="text"
                                    placeholder=${msg:getMessage("registration-label-surname")}
                                    class="form-control"
                                    pattern="${msg:getMessage("regex-surname")}"
                                    title="${msg:getMessage("hint-surname")}"
                                    value="${requestScope.newUser.getSurname()}"
                                    required>
                            </div>
                            <button type="submit" class="btn btn-success btn-block ">${msg:getMessage("login-button-register")}</button>
                    </form>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
        </div>
    </div>
    </body>
</html>
