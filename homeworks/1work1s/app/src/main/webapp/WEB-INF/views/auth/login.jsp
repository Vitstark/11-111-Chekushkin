<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="${pageContext.request.contextPath}/login_process" method="post">
    <div>
        <label for="email">Enter your email</label>
        <input type="text" id="email" name="email">
        <c:if test="${emailError != null}">
            <div style="color:red">
                <c:out value="${emailError}"></c:out>
            </div>
        </c:if>
    </div>

    <div>
        <label for="password">Enter a password</label>
        <input type="password" id="password" name="password">
        <c:if test="${passwordError != null}">
            <div style="color:red">
                <c:out value="${passwordError}"/>
            </div>
        </c:if>
    </div>

    <input type="submit" value="Sign in">
</form>

<a href="${pageContext.request.contextPath}/register">Registration</a>

</body>
</html>
