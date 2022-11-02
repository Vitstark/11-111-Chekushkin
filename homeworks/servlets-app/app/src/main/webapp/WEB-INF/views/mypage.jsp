<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>My Page</h2>
<div>
  <c:out value="Email: ${person.getEmail()}"></c:out>
</div>

<div>
  <c:out value="First name: ${person.getFirstName()}"></c:out>
</div>

<div>
  <c:out value="Last name: ${person.getLastName()}"></c:out>
</div>

<a href="${pageContext.request.contextPath}/change_person">Update my data</a>
</body>
</html>
