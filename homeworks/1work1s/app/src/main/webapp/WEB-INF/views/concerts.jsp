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
  <link href="styles/concerts.css" rel="stylesheet"/>
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
        <li class="nav-item"><a class="nav-link" href="#portfolio">Concerts</a></li>
        <c:if test="${isAdmin}">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/concerts/new">Add Concert</a>
          </li>
        </c:if>
        <li class="nav-item">
          <c:choose>
            <c:when test="${person == null}">
            <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
            </c:when>
            <c:otherwise>
              <a class="nav-link" href="${pageContext.request.contextPath}/mypage">${person.getName()}</a>
            </c:otherwise>
          </c:choose>
        </li>
      </ul>
    </div>
  </div>
</nav>
<!-- Masthead-->
<header class="masthead">
  <div class="container px-4 px-lg-5 h-100">
    <div class="row gx-4 gx-lg-5 h-100 align-items-center justify-content-center text-center">
      <div class="col-lg-8 align-self-end">
        <h1 class="text-white font-weight-bold">Ticket service for you and your family</h1>
        <hr class="divider"/>
      </div>
      <div class="col-lg-8 align-self-baseline">
        <p class="text-white-75 mb-5">Easy, Beauty and Fast!</p>
      </div>
    </div>
  </div>
</header>
<!-- Concerts -->
<div id="portfolio">
  <div class="container-fluid p-0">
    <div class="row g-0">
      <c:forEach var="concert" items="${concerts}">
        <div class="col-lg-4 col-sm-6">
          <a class="portfolio-box" href="${pageContext.request.contextPath}/concerts?id=${concert.getId()}" title="Concert">
            <img class="img-fluid" src="${concert.getImagePath()}" alt="..."/>
            <div class="portfolio-box-caption">
              <div class="project-category text-white-50">${concert.getAuthor()}</div>
              <div class="project-name">${concert.getTitle()}</div>
            </div>
          </a>
        </div>
      </c:forEach>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- SimpleLightbox plugin JS-->
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.js"></script>
<!-- Core theme JS-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
