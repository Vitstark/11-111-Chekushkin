<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <c:out value="Name: ${person.getName()}"></c:out>
</div>

<a href="${pageContext.request.contextPath}/change_person">Update my data</a>
<div>
  <c:forEach var="ticket" items="${person.getTickets()}">
    <div>
      <tr>
          <%--    <td>${presentation.getConcert().getAuthor()}</td>--%>
          <%--    <td>${presentation.getConcert().getTitle()}</td>--%>
        <td>${ticket.getPresentationId()} presentation </td>
        <td>${ticket.getRow()} row </td>
        <td>${ticket.getPlace()} place </td>
      </tr>
    </div>
  </c:forEach>

</div>
</body>
</html>
