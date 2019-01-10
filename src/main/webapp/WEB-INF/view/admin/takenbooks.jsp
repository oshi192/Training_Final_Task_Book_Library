<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Some Books example</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="<c:url value="/resources/bootstrap/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
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
						<th>${msg:getMessage("book-taken-user-email")}</th>
						<th>${msg:getMessage("book-taken-user-phone")}</th>
						<th>${msg:getMessage("book-taken-user-name")}</th>
						<th>${msg:getMessage("book-taken-user-surname")}</th>
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
                            <td><c:out value="${TakenBook.user.email}"/></td>
                            <td><c:out value="${TakenBook.user.phoneNumber}"/></td>
                            <td><c:out value="${TakenBook.user.name}"/></td>
                            <td><c:out value="${TakenBook.user.surname}"/></td>
                            <td><c:out value="${TakenBook.takeBeginDate}"/></td>
                            <td><c:out value="${TakenBook.takeEndDate}"/></td>
                            <td>
                                <c:if test="${locale =='en'}"> <c:out value="${Book.nameEn}"/></c:if>
                                <c:if test="${locale =='uk'}"> <c:out value="${Book.nameUk}"/></c:if>
                            </td>
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
