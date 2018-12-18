<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>


    <link href="<c:url value="/resources/css/flags.css" />" rel=stylesheet type="text/css">
    <link href="<c:url value="/resources/css/mainStyle.css" />" rel=stylesheet type="text/css">

    <div class="header">

        <div class="element-panel">
            <div class="lang lang-ua">
                <a href="?lang=ua"><img src="<c:url value="/resources/images/blank.gif" />" class="flag img-ua" />Ukr</a>
            </div>
            <div class="lang lang-en">
                <a href="?lang=en"><img src="<c:url value="/resources/images/blank.gif" />" class="flag img-en" />En</a>
            </div>
        </div>

        <div class="title">
            <h1>${msg:getMessage("library")}</h1>
        </div>

        <div class="right-panel">

            <c:choose>
                <c:when test="${user eq Nan}">
                    ${msg:getMessage("info-msg")}
                    <a href="${pageContext.request.contextPath}/login" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("login")} </a>
                    ${msg:getMessage("or")}
                    <a href="${pageContext.request.contextPath}/registration" class="w3-btn w3-hover-light-blue w3-round-large"> ${msg:getMessage("register")}</a>
                </c:when>

                <c:otherwise>
                    ${msg:getMessage("logged")}
                    <a href="${pageContext.request.contextPath}/cabinet">
                            ${user.email}
                    </a>
                    <a href="${pageContext.request.contextPath}/logout"> ${msg:getMessage("logout")}</a>
                </c:otherwise>
            </c:choose>
        </div>

    </div>