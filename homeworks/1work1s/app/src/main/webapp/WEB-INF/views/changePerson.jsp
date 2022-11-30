<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/change_person" method="post">
    <div>
        <label for="firstName">Enter your new first name</label>
        <input type="text" id="firstName" name="firstName">
        <c:if test="${firstNameError != null}">
            <div style="color:red">
                <c:out value="${firstNameError}"></c:out>
            </div>
        </c:if>
    </div>

    <div>
        <label for="lastName">Enter your new last name</label>
        <input type="text" id="lastName" name="lastName">
        <c:if test="${lastNameError != null}">
            <div style="color:red">
                <c:out value="${lastNameError}"></c:out>
            </div>
        </c:if>
    </div>

    <input type="submit" value="Change my acc">
</form>
</body>
</html>
