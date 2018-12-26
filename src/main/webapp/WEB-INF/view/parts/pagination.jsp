<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>

<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown">
        Action
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
         <a class="dropdown-item disabled" href="#">Action</a>
         <a class="dropdown-item" href="?recordsOnPage=10">10</a>
         <a class="dropdown-item" href="?recordsOnPage=20">20</a>
         <a class="dropdown-item" href="?recordsOnPage=50">50</a>
         <a class="dropdown-item" href="?recordsOnPage=100">100</a>
    </div>
</div>
<nav>
    <ul class="pagination">
        <li class="page-item"><a class="page-link"<c:if test="${currentPage > 1}">href="?currentPage=${currentPage-1}"</c:if>>Previous</a></li>
        <c:forEach begin="${firstPage}" end="${lastPage}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="active page-item"><b><a class="page-link" href="?currentPage=${i}">${i}</a></b></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?currentPage=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <li class="page-item"><a class="page-link" <c:if test="${lastPage > currentPage}">href="?currentPage=${currentPage+1}"</c:if>>Next</a></li>
    </ul>
</nav>

