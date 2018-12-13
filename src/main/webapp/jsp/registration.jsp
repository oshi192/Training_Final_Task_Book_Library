<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
<head>
    <title>Registration</title>

</head>

<body>
<div id="container">

    <div class="panel">
        <div class="form main-form">
            <form method="POST" action="${pageContext.request.contextPath}/submit-registration">

                <table class="plain-text">
                    <tr>
                        <td colspan="2"><h1>Registration form</h1></td>
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
                                   pattern="${msg:getMessage("regex-email")}"
                                   title="${msg:getMessage("hint-email")}"
                                   value="${requestScope.newUser.getEmail()}"></td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td><input name="password" type="password"
                                   pattern="${msg:getMessage("regex-password")}"
                                   title="${msg:getMessage("hint-password")}"
                                   value="${requestScope.newUser.getPassword()}"
                                   required></td>
                    </tr>
                    <tr>
                        <td>confirm password</td>
                        <td><input name="confirm-password" type="password"
                                   pattern="${msg:getMessage("regex-password")}"
                                   title="${msg:getMessage("hint-password")}"
                                   required></td>
                    </tr>
                    <tr>
                        <td>first name</td>
                        <td><input name="first-name" type="text"
                                   pattern="${msg:getMessage("regex-name")}"
                                   title="${msg:getMessage("hint-name")}"
                                   value="${requestScope.newUser.getName()}"
                                   required></td>
                    </tr>
                    <tr>
                        <td>surname</td>
                        <td><input name="surname" type="text"
                                   pattern="${msg:getMessage("regex-surname")}"
                                   title="${msg:getMessage("hint-surname")}"
                                   value="${requestScope.newUser.getSurname()}"
                                   required></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="register"></td>
                    </tr>

                </table>
            </form>
        </div>

    </div>
</div>
</body>
</html>
