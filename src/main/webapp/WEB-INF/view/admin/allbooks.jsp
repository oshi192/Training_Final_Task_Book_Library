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
						<td>${msg:getMessage("book-taken-name")}</th>
						<td>${msg:getMessage("book-taken-authors")}</th>
						<td>${msg:getMessage("book-taken-section")}</th>
						<td>${msg:getMessage("book-taken-edit")}</th>
						<td>${msg:getMessage("book-taken-remove")}</th>
					</tr>
				</thead>
				<tbody>
                    <c:forEach var="Book" items="${requestScope.Books}">
                        <tr class="table-active">
                            <td></td>
                            <td><c:out value="${Book.name}"/></td>
                            <td><c:out value="${Book.authors}"/></td>
                            <td><c:out value="${Book.section}"/></td>
                             <td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>
                             <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			<jsp:include page="../parts/pagination.jsp"/>
		</div>
	</div>
</div>

