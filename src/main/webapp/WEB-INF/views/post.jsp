<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.qbang.stockpedia.domain.Board" %>
<%@ page import="com.qbang.stockpedia.domain.Comment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스톡피디아</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<style>
</style>
</head>
<body>
<% Board board = (Board) request.getAttribute("content"); %>
<% List<Comment> list = (List<Comment>) request.getAttribute("comment"); %>
<% boolean like = (Boolean) request.getAttribute("like"); %>
<div class="container">
  <div class="mb-3 row">
    <label for="title" class="col-sm-2 col-form-label">글 제목</label>
    <div class="col-sm-10">
      <input type="text" readonly class="form-control-plaintext" id="title" value="<%= board.getTitle() %>">
    </div>
  </div>
  <div class="mb-3 row">
    <label for="content" class="col-sm-2 col-form-label">글 내용</label>
    <div class="col-10">
      <input type="text" readonly class="form-control-plaintext" id="content" value="<%= board.getContent() %>">
    </div>
  </div>
  <% if(!like) {%>
  <button type="button" onclick="location.href='like?board_num=<%= board.getBoard_num() %>'" class="btn btn-outline-danger">♡</button>
  <%}else {%>
  <button type="button" class="btn btn-outline-danger disabled">♥</button>
  <%} %>
  <ul class="list-group list-group-flush">
  	  <% if(list != null) {
    		for(int i=0; i<list.size(); i++){ %>
	  			<li class="list-group-item"><%= list.get(i).getContent() %></li>
	  <% 	} 
    	 }else{ %>
			<li class="list-group-item">댓글을 작성해보세요!</li>
		<% }%>
  </ul>
  <form action="comment" method="POST">
  	<div class="mb-3 row">
  		<input type="hidden" name="board_num" value="<%= board.getBoard_num() %>">
	    <div class="col-sm-8">
			<input type="text" class="form-control" name="comment" maxlength="90">
	 	</div>
	   	<div class="col-sm-4">
	   		<button type="submit" class="btn btn-secondary">확인</button>
	   	</div>
   	</div>
  </form>
</div>
</body>
</html>