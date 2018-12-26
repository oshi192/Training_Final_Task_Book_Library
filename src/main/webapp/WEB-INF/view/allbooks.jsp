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
    </head>

    <body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		<jsp:include page="parts/navbar.jsp"/>

			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("book-taken-name")}</th>
						<th>${msg:getMessage("book-taken-authors")}</th>
						<th>${msg:getMessage("book-taken-section")}</th>
					</tr>
				</thead>
				<tbody>
                    <c:forEach var="Book" items="${requestScope.Books}">
                        <tr class="table-active">
                            <td></td>
                            <td><c:out value="${Book.name}"/></td>
                            <td><c:out value="${Book.authors}"/></td>
                            <td><c:out value="${Book.section}"/></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			<jsp:include page="parts/pagination.jsp"/>
		</div>
	</div>
</div>

