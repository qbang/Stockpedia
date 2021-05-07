<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스톡피디아</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container">
  <main>
    <div class="row g-3">
      <div>
  		<h4 class="mb-3">글쓰기</h4>
        <form action="doWrite" method="POST">
        	<div class="mb-3">
			  <label for="title" class="form-label">제목</label>
			  <input type="text" class="form-control" id="title" name="title" placeholder="글 제목을 작성해주세요.">
			</div>
			<div class="mb-3">
			  <label for="content" class="form-label">내용</label>
			  <textarea class="form-control" id="content" name="content" rows="3" placeholder="글 내용을 작성해주세요."></textarea>
			</div>
			<button type="submit" class="btn btn-secondary">글쓰기</button>
        </form>
      </div>
    </div>
  </main>
</div>
</body>
</html>