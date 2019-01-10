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
<form method="POST" >
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("book-taken-name")}</th>
						<th>${msg:getMessage("book-taken-authors")}</th>
						<th>${msg:getMessage("book-taken-section")}</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
                    <c:forEach var="BookRequest" items="${requestScope.BooksRequests}" varStatus="status">
                       <tr class="table-active">
                           <td>${status.count+recordsOnPage*(currentPage-1)}.</td>
                            <td>
                                <c:if test="${locale =='en'}"> <c:out value="${Book.nameEn}"/></c:if>
                                <c:if test="${locale =='uk'}"> <c:out value="${Book.nameUk}"/></c:if>
                            </td>
                            <td><c:out value="${BookRequest.authors}"/></td>
                            <td><c:out value="${BookRequest.section}"/></td>
                            <td>
                                <button
                                    type="submit"
                                    name="remove"
                                    value="${BookRequest.id}"
                                    class="btn btn-xs btn-danger"
                                    data-title="Edit" data-toggle="modal"
                                    data-target="#edit" >
                                    <span class="glyphicon glyphicon-pencil"></span>
                                    ${msg:getMessage("button-denied")}
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			<jsp:include page="../parts/pagination.jsp"/>
		</div>
	</div>
</div>
