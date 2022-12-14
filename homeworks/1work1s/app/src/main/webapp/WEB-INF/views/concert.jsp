<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
  <meta name="description" content=""/>
  <meta name="author" content=""/>
  <title>Creative - Start Bootstrap Theme</title>
  <!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
  <!-- Bootstrap Icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
        rel="stylesheet"/>
  <!-- Google fonts-->
  <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet"/>
  <link
      href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
      rel="stylesheet" type="text/css"/>
  <!-- SimpleLightbox plugin CSS-->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css"
        rel="stylesheet"/>
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="styles/concert.css" rel="stylesheet"/>
  <style>
      header.masthead {
          padding-top: 10rem;
          padding-bottom: calc(10rem - 4.5rem);
          background: linear-gradient(to bottom, rgba(92, 77, 66, 0.8) 0%, rgba(92, 77, 66, 0.8) 100%), url(${pageContext.request.contextPath}/${concert.getImagePath()});
          background-position: center;
          background-repeat: no-repeat;
          background-attachment: scroll;
          background-size: cover;
      }
  </style>
</head>
<body id="page-top">
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
  <div class="container px-4 px-lg-5">
    <a class="navbar-brand" href="#page-top">Vitstark Tickets</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
            aria-expanded="false" aria-label="Toggle navigation"><span
        class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ms-auto my-2 my-lg-0">
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/concerts">Concerts</a></li>
        <c:if test="${isAdmin}">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/presentation/new?concertId=${concert.getId()}">
              Add Presentation</a>
          </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<!-- Concert-->
<header class="masthead">
  <div class="container px-4 px-lg-5 h-100">
    <div class="row gx-4 gx-lg-5 h-100 align-items-center justify-content-center text-center">
      <div class="col-lg-8 align-self-end">
        <h1 class="text-white font-weight-bold">${concert.getTitle()}</h1>
        <hr class="divider"/>
      </div>
      <div class="col-lg-8 align-self-baseline">
        <p class="text-white-75 mb-5">${concert.getAuthor()}</p>
        <p class="text-white-75 mb-5">${concert.getDescription()}</p>
      </div>
    </div>
  </div>
</header>
<!-- Tickets -->
<div class="card mb-4">
  <div class="card-header">
    Presentations
  </div>
  <div class="card-body">
    <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
      <div class="dataTable-top">
<%--        <form>--%>
<%--        </form>--%>
        <a href="${pageContext.servletContext.contextPath}/concerts?id=${concert.getId()}&year=${prevDate.getYear()}&month=${prevDate.getMonthValue()}&day=${prevDate.getDayOfMonth()}">Prev Day</a>
        <a href="${pageContext.servletContext.contextPath}/concerts?id=${concert.getId()}&year=${nextDate.getYear()}&month=${nextDate.getMonthValue()}&day=${nextDate.getDayOfMonth()}">Next Day</a>
      </div>
      <div class="dataTable-container">
        <table id="datatablesSimple" class="dataTable-table">
          <thead>
          <tr>
            <th data-sortable="" style="width: 19.8552%;"><a href="#"
                                                             class="dataTable-sorter">Author</a>
            </th>
            <th data-sortable="" style="width: 29.0589%;"><a href="#"
                                                             class="dataTable-sorter">Title</a>
            </th>
            <th data-sortable="" style="width: 15.7187%;"><a href="#"
                                                             class="dataTable-sorter">Time</a>
            </th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="presentation" items="${presentations}">
            <tr>
              <td><a href="${pageContext.servletContext.contextPath}/presentation?id=${presentation.getId()}">${concert.getAuthor()}</a></td>
              <td><a href="${pageContext.servletContext.contextPath}/presentation?id=${presentation.getId()}">${concert.getTitle()}</a></td>
              <td><a href="${pageContext.servletContext.contextPath}/presentation?id=${presentation.getId()}">
                  ${presentation.getTime()}</a></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <!-- Footer-->
  <footer class="bg-light py-5">
    <div class="container px-4 px-lg-5">
      <div class="small text-center text-muted">Copyright &copy; 2022 - Vitstark</div>
    </div>
  </footer>
  <!-- Bootstrap core JS-->
  <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <!-- SimpleLightbox plugin JS-->
  <script
      src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
  <!-- Core theme JS-->
  <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
  <script src="js/table.js"></script>
  <script src="js/datatables-simple-demo.js"></script>
</body>
</html>

