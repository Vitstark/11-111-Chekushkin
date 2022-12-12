<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Presentation</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<a href="${pageContext.request.contextPath}/concerts?id=${presentation.getConcert().getId()}">
  <h1>${presentation.getConcert().getTitle()}</h1>
</a>

<form action="${pageContext.request.contextPath}/presentations" method="post">
  <input type="hidden" name="id" value="${presentation.getId()}">
  <input type="hidden" name="_method" value="DELETE">
  <input type="submit" value="Delete this presentation">
</form>

<form action="${pageContext.request.contextPath}/presentations/new">
  <input type="hidden" name="concertId" value="${presentation.getConcert().getId()}">
  <input type="submit" value="create new presentation">
</form>

<c:forEach var="ticket" items="${presentation.getTickets()}">
  <div>
  </div>
</c:forEach>
</body>
</html>
