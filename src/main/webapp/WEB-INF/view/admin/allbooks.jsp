<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/bootstrap/js/jquery-3.2.1.min.js" />"></script>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>
</head>
<body>

<ul>
 <li><a href="${pageContext.request.contextPath}/taken-books" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-taken-book")}</a></li>
 <li><a href="${pageContext.request.contextPath}/all-books" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-all-book")}</a></li>
 <li><a href="${pageContext.request.contextPath}/all-users" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-all-users")}</a></li>
</ul>
<form method="post" action="">
    <h2>admin book list</h2>
    <table>
        <tr>
          <td></td>
          <td>${msg:getMessage("book-name")}</td>
        </tr>
        <c:forEach var="book" items="${requestScope.bookList}">
            <tr>
                <td><c:out value="${book.name}"/></td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <jsp:include page="../parts/pagination.jsp"/>

</body>
</html>