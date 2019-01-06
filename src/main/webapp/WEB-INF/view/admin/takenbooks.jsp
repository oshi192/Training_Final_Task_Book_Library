<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
<head>
    <title>Title</title>
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/bootstrap/js/jquery-3.2.1.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>

</head>
<body>

<ul>
 <li><a href="${pageContext.request.contextPath}/taken-books" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-taken-book")}</a></li>
 <li><a href="${pageContext.request.contextPath}/manage-books" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-all-book")}</a></li>
 <li><a href="${pageContext.request.contextPath}/all-users" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("admin-all-users")}</a></li>
</ul>
<form method="post" action="">
    <h2>admin book list</h2>
    <table class="table">
        <thead class="thead-dark">
            <tr>
              <th scope="col">${msg:getMessage("book-taken-name")}</td>
              <th scope="col">${msg:getMessage("book-taken-authors")}</td>
              <th scope="col">${msg:getMessage("book-taken-date")}</td>
              <th scope="col">${msg:getMessage("book-taken-end-date")}</td>
              <th scope="col">${msg:getMessage("book-taken-user-name")}</td>
              <th scope="col">${msg:getMessage("book-taken-user-surname")}</td>
              <th scope="col">${msg:getMessage("book-taken-user-email")}</td>
              <th scope="col">${msg:getMessage("book-taken-user-phone")}</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="TakenBook" items="${requestScope.TakenBooks}">
                <tr>
                    <th scope="row"><c:out value="${TakenBook.book.name}"/></td>
                    <td><c:out value="${TakenBook.authors}"/></td>
                    <td><c:out value="${TakenBook.takenDate}"/></td>
                    <td><c:out value="${TakenBook.wilReturnDate}"/></td>
                    <td><c:out value="${TakenBook.user.name}"/></td>
                    <td><c:out value="${TakenBook.user.surname}"/></td>
                    <td><c:out value="${TakenBook.user.email}"/></td>
                    <td><c:out value="${TakenBook.user.phoneNumber}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </form>
</body>
</html>