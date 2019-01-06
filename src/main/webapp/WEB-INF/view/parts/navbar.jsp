<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>

    <nav class="navbar navbar-expand-lg navbar-light bg-light static-top navbar-dark bg-dark">

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"><span class="navbar-toggler-icon"></span></button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">${msg:getMessage("library")}</a>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="navbar-nav">
                <li class="nav-item active">
                     <a class="nav-link" href="${pageContext.request.contextPath}/all-books">${msg:getMessage("all-books")} <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                     <a class="nav-link" href="${pageContext.request.contextPath}/manage-books">A${msg:getMessage("manage-books")} <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                     <a class="nav-link" href="${pageContext.request.contextPath}/manage-books">A all users with books<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                     <a class="nav-link" href="${pageContext.request.contextPath}/manage-books">U my books <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                     <a class="nav-link" href="${pageContext.request.contextPath}/manage-books">U take book <span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <ul class="navbar-nav ml-md-auto">
                <c:choose>
                    <c:when test="${user eq Nan}">
                        <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/login">${msg:getMessage("login")}<span class="sr-only">(current)</span></a></li>
                        <li class="nav-item active"><a class="nav-link" href="#">|</a><span class="sr-only">(current)</span></li>
                        <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/registration">${msg:getMessage("register")}<span class="sr-only">(current)</span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/cabinet"><span class="glyphicon glyphicon-user"></span>  ${user.email}</a></li>
                        <li class="nav-item active"><a class="nav-link" href="#">|</a><span class="sr-only">(current)</span></li>
                        <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> ${msg:getMessage("logout")}</a></li>
                    </c:otherwise>
                </c:choose>
                <ul class="lang">
                <li class=""><a href="?lang=En">En</a></li>
                <li class=""><a href="?lang=Ua">Ua</a></li>
                </ul>

            </ul>
        </div>
    </nav>
