<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>New Concert</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
<h1>Create new concert</h1>
<form action="${pageContext.request.contextPath}/concerts" method="post" enctype="multipart/form-data">
  <div class="text-field">
    <label for="title">Enter a title</label>
    <input type="text" id="title" name="title" style="width: 500px">
  </div>

  <div class="text-field">
    <label for="author">Enter a author</label>
    <input type="text" id="author" name="author" style="width: 500px">
  </div>

  <div class="text-field">
    <label for="description">Enter a description</label>
    <input type="text" id="description" name="description"
           size="50" style="width: 500px; height: 20px">
  </div>

  <div class="text-field">
    <label for="photo">Choose a image</label>
    <input type="file" id="photo" name="photo"/>
  </div>

  <div class="text-field">
    <input type="submit" value="Create">
  </div>
</form>
</body>
</html>
