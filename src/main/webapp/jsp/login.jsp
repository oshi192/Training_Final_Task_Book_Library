<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
<head>
    <title>Login page</title>

</head>

<body>
<div id="container">

    <div class="panel">
<jsp:include page="header.jsp"/>



        <div class="form main-form">
            <form method="POST" action="${pageContext.request.contextPath}/submit-login">

                <table class="plain-text">
                    <tr>
                        <td colspan="2"><h1>j</h1></td>
                    </tr>

                    <tr>
                        <td colspan="2" class="error-message">
                            <c:if test='${errorMessage!=""}'>
                                <c:out value="${errorMessage}"/>
                            </c:if>
                        </td>
                    </tr>

                    <tr>
                        <td>email</td>
                        <td><input name="email" type="text"
                                   pattern="[a-z0-9_-]{1,15}@[a-z0-9_-]{1,15}(\.[a-z0-9_-]{2,6})*\.[a-z]{2,6}$"
                                   title="${msg:getMessage("library")}"></td>
                    </tr>

                    <tr>
                        <td>password</td>
                        <td><input name="password" type="password"
                                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                                   required></td>
                    </tr>

                    <tr>
                        <td colspan="2"><input type="submit" value="Login"></td>
                    </tr>

                </table>
            </form>
        </div>

    </div>
</div>
</body>
</html>
