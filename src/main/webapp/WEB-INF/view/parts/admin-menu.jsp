<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
                <li>
                     <a href="${pageContext.request.contextPath}/all-users">
                          ${msg:getMessage("taken-books")}
                     </a>
                </li>
                <li>
                     <a href="${pageContext.request.contextPath}/books-requests">
                          ${msg:getMessage("books-requests")}
                     </a>
                </li>