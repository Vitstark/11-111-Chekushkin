<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h2>Create New Presentation</h2>
<div>
  <form action="${pageContext.request.contextPath}/presentation" method="post">
    <div>
      <label for="presentationTime">Enter a time of presentation in format YYYY-MM-DD HH:MM:SS</label>
      <input type="text" id="presentationTime" name="presentationTime">
    </div>

    <input type="hidden" name="concertId" value="${concertId}">

    <input type="submit" value="Create">
  </form>
</div>
</body>
</html>
