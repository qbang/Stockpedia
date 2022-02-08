<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" %>
<html>
<head>
	<title>영차영차</title>
	<link rel="stylesheet" href="https://bulma.io/vendor/fontawesome-free-5.15.2-web/css/all.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.css">
	<link rel="stylesheet" href="https://bulma.io/css/bulma-docs.min.css?v=202105031854"></head>
	<style>
		.container{
			margin-top: 20px;
			width: 20%;
		}
		button{
			width: 100%;
		}
	</style>
</head>

<body>
<% String warning = (String) request.getAttribute("warning"); %>
<div class="container">
	<% if (warning != null) { %>
	<div class="notification is-danger is-light">${warning}</div>
	<% } %>
  <form action="member" method="post">
<%--    <img src="resources/gif/main_animation.gif">--%>
 	<div class="field">
	  <div class="control">
	    <input class="input" type="text" name="uid" placeholder="아이디를 입력하세요.">
	  </div>
  	</div>
 	 <div class="field">
	  <div class="control">
	    <input class="input" type="text" name="unick" placeholder="닉네임을 입력하세요.">
	  </div>
  	</div>
	<div class="field">
	  <div class="control">
	    <input class="input" type="password" name="upw" placeholder="비밀번호를 입력하세요.">
	  </div>
  	</div>
  	<button class="button is-primary">확인</button>
  </form>
</div>
</body>
</html>
