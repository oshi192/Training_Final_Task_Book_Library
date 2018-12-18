<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Some Books example</title>
    <link rel="viewport" >
    <link rel="stylesheet" href="<c:url value="/resources/css/mainStyle.css" />">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-center-align">
<jsp:include page="header.jsp"/>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <a href="${pageContext.request.contextPath}/registration" class="w3-btn w3-hover-light-blue w3-round-large">register</button>
        <a href="${pageContext.request.contextPath}/login" class="w3-btn w3-hover-light-blue w3-round-large">log in</a>
        <a href="${pageContext.request.contextPath}/book-list" class="w3-btn w3-hover-green w3-round-large" >book list</button>
    </div>
</div>
</body>
</html>