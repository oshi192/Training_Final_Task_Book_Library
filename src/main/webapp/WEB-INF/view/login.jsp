<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
    <head>
        <title>Login page</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
<<<<<<< HEAD
        <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
=======
        <link href="<c:url value="/resources/css/login5.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/flags.css" />" rel="stylesheet">
>>>>>>> edfd3089b9d81b68c9a8db558a42af5700b5ef02
        <script src="<c:url value="/resources/bootstrap/js/jquery-3.2.1.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
    </head>
    <body>
    <div class="container-fluid bg">
<<<<<<< HEAD
=======
     <jsp:include page="parts/navbar.jsp"/>
>>>>>>> edfd3089b9d81b68c9a8db558a42af5700b5ef02
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <div id="log">
                    <form  method="POST" action="${pageContext.request.contextPath}/submit-login">
                    <h1>${msg:getMessage("login-please-login")}</h1>
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
<<<<<<< HEAD
                    <img class="img img-responsive img-circle" src="<c:url value="/resources/image/user1.png" />"/>
=======
                    <img class="rounded mx-auto d-block img img-responsive img-circle" src="<c:url value="/resources/image/user1.png" />"/>
>>>>>>> edfd3089b9d81b68c9a8db558a42af5700b5ef02
                        <div class="form-group">
                            <label>${msg:getMessage("login-label-email")}</label>
                            <input name="email" type="email" class="form-control" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label>${msg:getMessage("login-label-password")}</label>
                            <input name="password" type="password" class="form-control" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-success btn-block ">${msg:getMessage("login-button-login")}</button>
                         </form>
                        <a href="${pageContext.request.contextPath}/registration">
                            <button class="btn btn-danger btn-block ">${msg:getMessage("login-button-register")}</button>
                        </a>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
        </div>
    </div>
    </body>
</html>
