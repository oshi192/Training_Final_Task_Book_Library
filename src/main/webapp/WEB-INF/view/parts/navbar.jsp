<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>

<nav class="navbar navbar-inverse ">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">${msg:getMessage("library")}</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active">
            <a href="${pageContext.request.contextPath}/all-books">
                ${msg:getMessage("all-books")}
            </a>
        </li>
        <c:if test="${user.role == 'ADMIN'}">
            <jsp:include page="admin-menu.jsp"/>
        </c:if>
        <c:if test="${user.role == 'USER'}">
            <jsp:include page="usermenu.jsp"/>
        </c:if>
      </ul>
      <ul class="nav navbar-nav navbar-right">

            <c:choose>
                  <c:when test="${user eq Nan}">
                      <li>
                          <a href="${pageContext.request.contextPath}/registration">
                              <span class="glyphicon glyphicon-user"></span>
                              ${msg:getMessage("register")}<span class="sr-only">
                          </a>
                      </li>
                      <li>
                          <a href="${pageContext.request.contextPath}/login">
                              <span class="glyphicon glyphicon-log-in"></span>
                              ${msg:getMessage("login")}
                          </a>
                      </li>
                  </c:when>
                  <c:otherwise>
                      <li class="nav-item active">
                          <a href="#">
                              <span class="glyphicon glyphicon-user"></span>
                              ${user.email}
                          </a>
                      </li>
                      <li class="nav-item active">
                      <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                      <span class="glyphicon glyphicon-log-out"></span>
                      ${msg:getMessage("logout")}</a>
                      </li>
                  </c:otherwise>
              </c:choose>
              <li>
              <ul class="lang">
                <li><a href="?lang=En">En   .</a></li>
                <li><a href="?lang=Uk">Ua   .</a></li>
                </ul>
                </li>
      </ul>
    </div>
  </div>
</nav>
