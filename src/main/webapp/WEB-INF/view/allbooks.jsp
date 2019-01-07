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
		<jsp:include page="parts/navbar.jsp"/>
		<jsp:include page="parts/searchpanel.jsp"/>

			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("book-taken-name")}</th>
						<th>${msg:getMessage("book-taken-authors")}</th>
						<th>${msg:getMessage("book-taken-section")}</th>
						<c:if test="${user.role == 'ADMIN'}">
						<th></th>
						<th></th>
                        </c:if>
                        <c:if test="${user.role == 'USER'}">
                        <th></th>
                        </c:if>
					</tr>
				</thead>
				<tbody>
                    <c:forEach var="Book" items="${requestScope.Books}" varStatus="status">
                        <tr class="table-active">
                            <td>${status.count+recordsOnPage*(currentPage-1)}
                            <td><c:out value="${Book.name}"/></td>
                            <td><c:out value="${Book.authors}"/></td>
                            <td><c:out value="${Book.section}"/></td>
                            <c:if test="${user.role == 'ADMIN'}">
                                <td>
                                    <button
                                        type="submit"
                                        name="edit"
                                        value="${Book.id}"
                                        class="btn btn-success btn-xs"
                                        data-title="Edit" data-toggle="modal"
                                        data-target="#edit" >
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        ${msg:getMessage("button-edit")}
                                    </button>
                                </td>
                                <td>
                                    <button
                                        type="submit"
                                        name="remove"
                                        value="${Book.id}"
                                        class="btn btn-danger btn-xs"
                                        data-title="Edit" data-toggle="modal"
                                        data-target="#edit" >
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        ${msg:getMessage("button-remove")}
                                    </button>
                                </td>                            </c:if>
                            <c:if test="${user.role == 'USER'}">
                                <td>
                                    <button
                                        type="submit"
                                        name="take"
                                        value="${Book}"
                                        class="btn btn-warning btn-xs"
                                        data-title="Edit" data-toggle="modal"
                                        data-target="#edit" >
                                        <span class="glyphicon glyphicon-pencil"></span>
                                        ${msg:getMessage("button-take")}
                                    </button>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			<jsp:include page="parts/pagination.jsp"/>
		</div>
	</div>
</div>

