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
          <td></td>
          <td>${msg:getMessage("book-name")}</td>
          <td>${msg:getMessage("book-authors")}</td>
          <td>${msg:getMessage("book-location")}</td>
        </tr>
        <c:forEach var="book" items="${requestScope.allBooks}">
            <tr>
                <td>
                <c:if test='${book.available!=""}'>
                    <button type="submit" name="take-book" value="${book.id}">${msg:getMessage("take-book")}</button>
                </c:if>                    
                </td>
                <td><c:out value="${book.name}"/></td>
                <td><c:out value="${book.authors}"/></td>
                <td><c:out value="${book.location}"/></td>
            </tr>
        </c:forEach>
    </table>
    </form>
</body>
</html>