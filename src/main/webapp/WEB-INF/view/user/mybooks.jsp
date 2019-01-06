<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>
 <li><a href="${pageContext.request.contextPath}/registration" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("user-taken-books")}</a></li>
 <li><a href="${pageContext.request.contextPath}/registration" class="w3-btn w3-hover-light-blue w3-round-large">${msg:getMessage("user-all-books")}</a></li>
</ul>
<form method="post" action="">
    <h2>admin book list</h2>
    <table>
        <tr>
          <td><input type="submit" value="go" name="button"></td>
          <td></td>
          <td><input class="filter" type="number" name="filter_prcntInOneMonthUAH_start"><input class="filter" type="number" name="filter_prcntInOneMonthUAH_end"></td>
          <td><input class="filter" type="number" name="filter_prcntInSixMonthUAH_start"><input class="filter" type="number" name="filter_prcntInSixMonthUAH_end"></td>
        </tr>
        <tr>
          <td>${msg:getMessage("book-taken-name")}</td>
          <td>${msg:getMessage("book-taken-authors")}</td>
          <td>${msg:getMessage("book-taken-date")}</td>
          <td>${msg:getMessage("book-taken-end-date")}</td>
        </tr>
        <c:forEach var="UserTakenBook" items="${requestScope.UserTakenBooks}">
            <tr>
                <td><c:out value="${UserTakenBook.book.name}"/></td>
                <td><c:out value="${UserTakenBook.authors}"/></td>
                <td><c:out value="${UserTakenBook.takenDate}"/></td>
                <td><c:out value="${UserTakenBook.wilReturnDate}"/></td>

            </tr>
        </c:forEach>
    </table>
    </form>
</body>
</html>