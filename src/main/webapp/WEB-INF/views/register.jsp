<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>스톡피디아</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<style>
		html,
		body {
		  height: 100%;
		}
		
		body {
		  display: flex;
		  align-items: center;
		  padding-top: 40px;
		  padding-bottom: 40px;
		  background-color: #f5f5f5;
		}
		
		.form-signin {
		  width: 100%;
		  max-width: 330px;
		  padding: 15px;
		  margin: auto;
		}
		.form-signin .checkbox {
		  font-weight: 400;
		}
		.form-signin .form-control {
		  position: relative;
		  box-sizing: border-box;
		  height: auto;
		  padding: 10px;
		  font-size: 16px;
		}
		.form-signin .form-control:focus {
		  z-index: 2;
		}
		.form-signin input[type="email"] {
		  margin-bottom: -1px;
		  border-bottom-right-radius: 0;
		  border-bottom-left-radius: 0;
		}
		.form-signin input[type="password"] {
		  margin-bottom: 10px;
		  border-top-left-radius: 0;
		  border-top-right-radius: 0;
		}
	</style>
</head>
<body class="text-center">
	<main class="form-signin">
	  <form action="doRegister" method="POST">
	    <img class="mb-4" src="resources/img/logo.svg" alt="" width="72" height="57">
	    <h1 class="h3 mb-3 fw-normal">스톡피디아의 회원이 되어 다양한 서비스를 이용해보세요.</h1>
	    <label for="inputId" class="visually-hidden">ID</label>
	    <input type="text" name="uid" class="form-control" placeholder="아이디를 입력하세요." required autofocus>
	    <label for="inputNick" class="visually-hidden">NickName</label>
	    <input type="text" name="unick" class="form-control" placeholder="별명을 입력하세요." required autofocus>
	    <label for="inputPassword" class="visually-hidden">Password</label>
	    <input type="password" name="upw" class="form-control" placeholder="비밀번호를 입력하세요." required>
	    <button class="w-100 btn btn-lg btn-primary" type="submit">회원가입</button>
	    <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
	  </form>
	</main>
</body>
</html>
