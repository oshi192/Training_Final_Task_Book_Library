<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

</head>
<body>

        <h1>RegisterPage</h1><br/>
        <form method="post" action="${pageContext.request.contextPath}/login">

            <input type="text" name="name"><br/>
            <input type="password" name="password"><br/><br/>
            <input class="button" type="submit" value="register">

        </form>
        <br/>
        <a href="${pageContext.request.contextPath}/">На головну</a>

</body>
</html>