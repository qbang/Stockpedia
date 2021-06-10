<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.qbang.stockpedia.domain.Board" %>
<%@ page import="com.qbang.stockpedia.domain.CommentTier" %>
<!DOCTYPE html>
<html>
<head>
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
			margin-bottom: 30px;
		}
		.post__head{
			font-weight: bold;
		}
		.post__like{
			height: 17px;
			width: 17px;
			margin-bottom: 20px;
		}
		.post__tier_img{
			margin-left: 10px;
			width: 20px;
			height: 20px;
		}
		article{
			margin-top: 20px;
			margin-bottom: 20px;
		}
	</style>
</head>
<body>
<% Board board = (Board) request.getAttribute("content"); %>
<% List<CommentTier> list = (List<CommentTier>) request.getAttribute("comment"); %>
<% boolean like = (Boolean) request.getAttribute("like"); %>
<div class="container">
    <div class="box">
   	<% if(like) {%>
	<img class="post__like" src="resources/svg/heart.svg">
	<%}else {%>
	<img class="post__like" onclick="location.href='like?board_num=<%= board.getBoard_num() %>'" src="resources/svg/heart_empty.svg">
	<%} %>
	<p class="post__head" ><%= board.getTitle() %></p>
	<p><%= board.getContent() %></p>
    </div>
	
<% if(list != null) {
for(int i=0; i<list.size(); i++){ %>
<article class="media">
	<figure class="media-left">
		<% if(list.get(i).getTier_num() == 5){%>
		<img src="resources/svg/seed.svg" class="post__tier_img">
		<% }else if(list.get(i).getTier_num() == 4){%>
		<img src="resources/svg/leaf.svg" class="post__tier_img">
		<% }else if(list.get(i).getTier_num() == 3){%>
		<img src="resources/svg/plant.svg" class="post__tier_img">
		<% }else if(list.get(i).getTier_num() == 2){%>
		<img src="resources/svg/tree.svg" class="post__tier_img">
		<% }else if(list.get(i).getTier_num() == 1){%>
		<img src="resources/svg/moon.svg" class="post__tier_img">
		<% }%>
	</figure>
	<div class="media-content">
		<div class="content">
			<p class="post__comment"><%= list.get(i).getContent() %></p>
		</div>
	</div>
</article>
<% } }%>

<form action="comment" method="POST">
	<input class="input" style="float:left; width:70%;" type="text" name="comment" placeholder="댓글을 작성해보세요!" maxlength="80">
	<button style="float:left; width:27%; margin-left: 3%;" type="submit" class="button is-primary is-outlined">확인</button>
	<input type="hidden" name="board_num" value="<%= board.getBoard_num() %>">
</form>
  
</div>
</body>
</html>