<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영차영차</title>
	<link rel="stylesheet" href="https://bulma.io/vendor/fontawesome-free-5.15.2-web/css/all.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.css">
	<link rel="stylesheet" href="https://bulma.io/css/bulma-docs.min.css?v=202105031854"></head>
<style>
	.container{
		margin-top: 30px;
		width: 20%;
	}
	img{
		height: 15px;
		width: 15px;
	}
	button{
		width: 100%;
	}
</style>
</head>

<body>
<div class="container">
	<article class="message">
		<div class="message-body">
			<img src="resources/svg/attention.svg">
			내용에 욕설이 포함되거나, 갈등을 조장할 수 있는 글일 경우 경고 없이 삭제 처리될 수 있습니다.
			<img src="resources/svg/attention.svg">
		</div>
	</article>

	<form action="doWrite" method="POST">
		<div class="field">
			<div class="control">
				<input class="input" type="text" name="title" placeholder="제목을 입력해주세요.">
			</div>
		</div>
		<div class="field">
			<div class="control">
				<textarea class="textarea" name="content" placeholder="내용을 입력해주세요."></textarea>
			</div>
		</div>
		<div class="buttons">
			<button class="button is-primary" type="submit">확인</button>
		</div>
	</form>

</div>

</body>

</html>