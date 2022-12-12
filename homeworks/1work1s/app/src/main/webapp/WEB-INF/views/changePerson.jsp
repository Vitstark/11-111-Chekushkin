<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/change_person" method="post">
    <div>
        <label for="name">Enter your new name</label>
        <input type="text" id="name" name="name">
        <c:if test="${nameError != null}">
            <div style="color:red">
                <c:out value="${nameError}"></c:out>
            </div>
        </c:if>
    </div>

    <input type="submit" value="Change my acc">
</form>
</body>
</html>
