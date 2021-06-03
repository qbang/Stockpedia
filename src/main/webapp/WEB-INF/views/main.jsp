<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>스톡피디아</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<style>
		.btn-secondary,
		.btn-secondary:hover,
		.btn-secondary:focus {
		  color: #333;
		  text-shadow: none; /* Prevent inheritance from `body` */
		}
		
		body {
		  text-shadow: 0 .05rem .1rem rgba(0, 0, 0, .5);
		  box-shadow: inset 0 0 5rem rgba(0, 0, 0, .5);
		}
		
		.cover-container {
		  max-width: 42em;
		}
		
		.nav-masthead .nav-link {
		  padding: .25rem 0;
		  font-weight: 700;
		  color: rgba(255, 255, 255, .5);
		  background-color: transparent;
		  border-bottom: .25rem solid transparent;
		}
		
		.nav-masthead .nav-link:hover,
		.nav-masthead .nav-link:focus {
		  border-bottom-color: rgba(255, 255, 255, .25);
		}
		
		.nav-masthead .nav-link + .nav-link {
		  margin-left: 1rem;
		}
		
		.nav-masthead .active {
		  color: #fff;
		  border-bottom-color: #fff;
		}
	</style>
</head>
<body class="d-flex h-100 text-center text-white bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
	  <header class="mb-auto">
	    <div>
	      <h3 class="float-md-start mb-0">스톡피디아</h3>
	    </div>
	  </header>
	  <main class="px-3">
	  	<h1>안녕하세요</h1>
	    <p class="lead">적은 돈으로도 투자할 수 있는 주식 상품을 찾아보세요.</p>
	    <p class="lead">
	      <a href="detail" class="btn btn-lg btn-secondary fw-bold border-white bg-white">구경하기</a>
	    </p>
	  </main>
	
	  <footer class="mt-auto text-white-50">
	    <p>made by qbang</p>
	  </footer>
	</div>
</body>
</html>
