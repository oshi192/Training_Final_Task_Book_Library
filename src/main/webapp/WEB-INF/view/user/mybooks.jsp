<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Some Books example</title>
        <link rel="viewport" >
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap-theme.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/bootstrap/js/jquery-3.2.1.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
        <link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />">

    </head>

    <body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		<jsp:include page="../parts/navbar.jsp"/>

			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("book-taken-takenDate")}</th>
						<th>${msg:getMessage("book-taken-wilReturnDate")}</th>
						<th>${msg:getMessage("book-taken-name")}</th>
						<th>${msg:getMessage("book-taken-authors")}</th>
						<th>${msg:getMessage("book-taken-section")}</th>
					</tr>
				</thead>
				<tbody>
                    <c:forEach var="TakenBook" items="${requestScope.TakenBooks}"  varStatus="status">
                        <tr class="table-active">
                            <td>${status.count+recordsOnPage*(currentPage-1)}.</td>
                            <td><c:out value="${TakenBook.takenDate}"/></td>
                            <td><c:out value="${TakenBook.wilReturnDate}"/></td>
                            <td><c:out value="${TakenBook.name}"/></td>
                            <td><c:out value="${TakenBook.authors}"/></td>
                            <td><c:out value="${TakenBook.section}"/></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			<jsp:include page="../parts/pagination.jsp"/>
		</div>
	</div>
</div>
