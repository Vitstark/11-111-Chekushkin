<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Register</title>
  <link href="styles/registration.css" rel="stylesheet"/>
</head>
<body>
<div class="textTitle">Registration</div>
<div class="outer-box">

  <form action="${pageContext.request.contextPath}/register" method="post">
    <a class="in" href="${pageContext.request.contextPath}/login">Sign In</a>
    <input type="submit" class="in" value="Sign Up"/>

    <div class="box">
      <div>
        <label for="email">Enter a email</label>
        <input type="text" class="text-field" id="email" name="email">
        <c:if test="${emailError != null}">
          <div style="color:red">
            <c:out value="${emailError}"></c:out>
          </div>
        </c:if>
      </div>

      <div>
        <label for="name">Enter your name</label>
        <input type="text" class="text-field" id="name" name="name">
        <c:if test="${nameError != null}">
          <div style="color:red">
            <c:out value="${nameError}"></c:out>
          </div>
        </c:if>
      </div>

      <div>
        <label for="password">Enter a password</label>
        <input type="password" class="text-field" id="password" name="password">
        <c:if test="${passwordError != null}">
          <div style="color:red">
            <c:out value="${passwordError.toString()}"/>
          </div>
        </c:if>
      </div>
    </div>
  </form>
</div>
</body>
</html>
