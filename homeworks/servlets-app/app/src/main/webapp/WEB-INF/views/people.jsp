<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="person" items="${people}">
    <div>
        <c:out value="${person.getFirstName()}"/>
        <c:out value="${person.getLastName()}"/>
        <c:out value="${person.getEmail()}"/>
    </div>
</c:forEach>
</body>
</html>
