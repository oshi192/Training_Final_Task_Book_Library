<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            border-collapse: collapse;
        }
        .filter{
        max-width:100px;
        }
        table, th, td {
        border: 1px solid black;
        }
    </style>
</head>
<body>
<form method="post" action="">
    <h2>available books:</h2>
    <table>
        <tr>
          <td><input type="submit" value="go" name="buttonDeposit"></td>
          <td></td>
          <td><input class="filter" type="number" name="filter_prcntInOneMonthUAH_start"><input class="filter" type="number" name="filter_prcntInOneMonthUAH_end"></td>
          <td><input class="filter" type="number" name="filter_prcntInSixMonthUAH_start"><input class="filter" type="number" name="filter_prcntInSixMonthUAH_end"></td>
        </tr>
        <tr>
          <td></td>
          <td><a href="?bookSortBy=bankNameD">Book</a></td>
          <td><a href="?DsortBy=prcntInOneMonthUAH">author</a></td>
          <td><a href="?DsortBy=prcntInSixMonthUAH">is available</a></td>

        </tr>
        <c:forEach var="BookAndAuthors" items="${requestScope.BooksAndAuthors}">
            <tr>
                <td><input type="button" name="book_id" value="${BookAndAuthors.bookId}"></td>
                <td><c:out value="${BookAndAuthors.bookName}"/></td>
                <td><c:out value="${BookAndAuthors.authors}"/></td>
                <td><c:out value="${BookAndAuthors.bookSection}"/></td>


            </tr>
        </c:forEach>
    </table>
        <input type="submit" value="Sent" name="choose" method="post"><br>
    </form>
</body>
</html>