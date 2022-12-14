<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login</title>
    <link href="styles/login.css" rel="stylesheet"/>
</head>
<body>
<div class="textTitle">Authorization</div>
<div class="outer-Box">
    <form action="${pageContext.request.contextPath}/login_process" method="post">

        <input type="submit" class="in" value="Sign in">
        <a class="in" href="${pageContext.request.contextPath}/register">Registration</a>

        <div class="box">
            <div>
                <label class="first" for="email">Enter your email</label>
                <input type="text" class="text-field" id="email" name="email">
                <c:if test="${emailError != null}">
                    <div style="color:red">
                        <c:out value="${emailError}"></c:out>
                    </div>
                </c:if>
            </div>

            <div>
                <label for="password">Enter a password</label>
                <input type="password" class="text-field" id="password" name="password">
                <c:if test="${passwordError != null}">
                    <div style="color:red">
                        <c:out value="${passwordError}"/>
                    </div>
                </c:if>
            </div>
        </div>
    </form>
</div>
</body>
</html>
