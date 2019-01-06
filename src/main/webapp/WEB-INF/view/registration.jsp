<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
    <head>
        <title>Login page</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/bootstrap/js/jquery-3.2.1.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
    </head>
    <body>
    <div class="container-fluid bg">
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">

                <div id="log">
                    <form  method="POST" action="${pageContext.request.contextPath}/submit-registration">
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
                        <img class="img img-responsive img-circle" src="<c:url value="/resources/image/user1r.png" />"/>
                            <div class="form-group">
                                <label>${msg:getMessage("login-label-email")}</label>
                                <input name="email" type="email" class="form-control"
                                       pattern="${msg:getMessage("regex-email")}"
                                       title="${msg:getMessage("hint-email")}"
                                       value="${requestScope.newUser.getEmail()}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>${msg:getMessage("registration-label-phone-number")}</label>
                                <input name="phone-number" type="text" class="form-control"
                                       pattern="${msg:getMessage("regex-phone")}"
                                       title="${msg:getMessage("hint-phone")}"
                                       value="${requestScope.newUser.getPhoneNumber()}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>${msg:getMessage("registration-label-password")}</label>
                                <input name="password" type="password" class="form-control"
                                       pattern="${msg:getMessage("regex-password")}"
                                       title="${msg:getMessage("hint-password")}"
                                       value="${requestScope.newUser.getPassword()}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>${msg:getMessage("registration-label-confirm-password")}</label>
                                <input name="confirm-password" type="password" class="form-control"
                                       pattern="${msg:getMessage("regex-password")}"
                                       title="${msg:getMessage("hint-password")}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>${msg:getMessage("registration-label-first-name")}</label>
                                <input name="first-name" type="text" placeholder="First name"
                                       class="form-control"
                                       pattern="${msg:getMessage("regex-name")}"
                                       title="${msg:getMessage("hint-name")}"
                                       value="${requestScope.newUser.getName()}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label>${msg:getMessage("registration-label-surname")}</label>
                                <input name="surname" type="text" class="form-control"
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
