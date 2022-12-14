<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="${pageContext.request.contextPath}/register" method="post">
    <div>
        <label for="email">Enter a email</label>
        <input type="text" id="email" name="email">
        <c:if test="${emailError != null}">
            <div style="color:red">
                <c:out value="${emailError}"></c:out>
            </div>
        </c:if>
    </div>

    <div>
        <label for="firstName">Enter your first name</label>
        <input type="text" id="firstName" name="firstName">
        <c:if test="${firstNameError != null}">
            <div style="color:red">
                <c:out value="${firstNameError}"></c:out>
            </div>
        </c:if>
    </div>

    <div>
        <label for="lastName">Enter your last name</label>
        <input type="text" id="lastName" name="lastName">
        <c:if test="${lastNameError != null}">
            <div style="color:red">
                <c:out value="${lastNameError}"></c:out>
            </div>
        </c:if>
    </div>

    <div>
        <label for="password">Enter a password</label>
        <input type="password" id="password" name="password">
        <c:if test="${passwordError != null}">
            <div style="color:red">
                <c:out value="${passwordError.toString()}"/>
            </div>
        </c:if>
    </div>

    <input type="submit" value="Sign up">
</form>

<a href="${pageContext.request.contextPath}/login">Login</a>
</body>
</html>
