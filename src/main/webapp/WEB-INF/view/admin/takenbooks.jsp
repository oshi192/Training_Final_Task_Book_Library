<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>
 <li><a href="${pageContext.request.contextPath}/taken-books" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-taken-book")}</a></li>
 <li><a href="${pageContext.request.contextPath}/manage-books" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-all-book")}</a></li>
 <li><a href="${pageContext.request.contextPath}/all-users" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-all-users")}</a></li>
</ul>
<form method="post" action="">
    <h2>admin book list</h2>
    <table>
        <tr>
          <td>${msg:getMessage("book-taken-name")}</td>
          <td>${msg:getMessage("book-taken-authors")}</td>
          <td>${msg:getMessage("book-taken-date")}</td>
          <td>${msg:getMessage("book-taken-end-date")}</td>
          <td>${msg:getMessage("book-taken-user-name")}</td>
          <td>${msg:getMessage("book-taken-user-surname")}</td>
          <td>${msg:getMessage("book-taken-user-email")}</td>
          <td>${msg:getMessage("book-taken-user-phone")}</td>
        </tr>
        <c:forEach var="TakenBook" items="${requestScope.TakenBooks}">
            <tr>
                <td><c:out value="${TakenBook.book.name}"/></td>
                <td><c:out value="${TakenBook.authors}"/></td>
                <td><c:out value="${TakenBook.takenDate}"/></td>
                <td><c:out value="${TakenBook.wilReturnDate}"/></td>
                <td><c:out value="${TakenBook.user.name}"/></td>
                <td><c:out value="${TakenBook.user.surname}"/></td>
                <td><c:out value="${TakenBook.user.email}"/></td>
                <td><c:out value="${TakenBook.user.phoneNumber}"/></td>
            </tr>
        </c:forEach>
    </table>
    </form>
</body>
</html>