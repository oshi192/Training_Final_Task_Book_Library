<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>

<nav aria-label="Page navigation">
  <ul class="pagination">
    <%--For displaying Previous link except for the 1st page --%>
      <c:if test="${currentPage != 1}">
        <a href="page=${currentPage - 1}" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
    </c:if>
    <c:forEach begin="1" end="${numOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="active"><a href="?page=1">1 <span class="sr-only">(current)</span></a></li>
            </c:when>
            <c:otherwise>
                 <li><a href="?page=${i}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt numOfPages}">
        <li>
          <a href="?page=${currentPage + 1}" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
    </c:if>

  </ul>
</nav>