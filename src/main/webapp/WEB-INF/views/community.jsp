<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.qbang.stockpedia.domain.Board" %>
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
				margin-bottom: 30px
			}
			.p_date{
				color: #B5B5B5;
				font-size: small;
			}
			.p_head{
				font-weight: bold;
			}
			.p_bottom{
				text-align: center;
				color: #7A7A7A;
			}
			img{
				height: 15px;
				width: 15px;
				margin: 2px;
			}
			.community__section_bottom{
				display: flex;
				justify-content: center;
				algin-content: center;
			}
			.community__box{
				color: #EBFFFC;
			}
			button{
				width: 100%;
				margin-bottom: 20px;
				font-weight: bold;
			}
		</style>
	</head>
</head>

<body>
<% List<Board> list = (List<Board>) request.getAttribute("list"); %>
<div class="container">
	<button class="button is-primary" onclick="location.href='reqWrite'">포스트 쓰러 가기!</button>
	 <% if(list != null) {
	   	for(int i=0; i<list.size(); i++){ %>
	      <div class="box" onclick="location.href='post?board_num=<%= list.get(i).getBoard_num() %>'">
	        <p class="p_date" ><%= list.get(i).getReg_date() %></p>
	      	<p class="p_head"><%= list.get(i).getTitle() %></p>
	        <p><%= list.get(i).getContent() %></p>
	      </div>
	  	<% 	} 
   	}else{%>
		<p>데이터 없음</p>
	<% }%>
	<div class="community__section_bottom">
		<img src="resources/svg/letter.svg">
		<span class="p_bottom">포스트를 모두 읽으셨어요!</span>
		<img src="resources/svg/letter.svg">
	</div>
</div>
</body>

</html>