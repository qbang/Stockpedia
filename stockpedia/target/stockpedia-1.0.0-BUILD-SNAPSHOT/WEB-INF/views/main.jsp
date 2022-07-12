<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.qbang.stockpedia.domain.Board" %>

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
		margin-bottom: 15px;
	}
	.container .main div{
		float: left;
		display: flex;
		flex-flow: column;
		justify-content: center;
		algin-content: center;
		height : 200px;
	}
	.right_{
		float: right;
	}

</style>
</head>
<body>
<% List<Board> list = (List<Board>) request.getAttribute("list"); %>
<div class="container">
	<div class="main">
		<div>
			<h2 class="title pb-1">어서오세요, ${unick}님!</h2>
			<h3 class="subtitle">적은 돈으로도 투자할 수 있는 주식을 찾아보세요.</h3>
		</div>
		<%--		<div>--%>
		<%--			<img height="200" width="200" src="resources/gif/main_animation.gif">--%>
		<%--		</div>--%>
	</div>

	<table class="table is-hoverable is-fullwidth">
		<thead>
		<tr>
			<th colspan="2">가격</th>
		</tr>
		</thead>
		<tbody>
		<tr onclick="location.href='stock?priceIdx=0&postIdx=0'">
			<td>5,000원 미만</td>
			<td><span class="tag is-primary right_">${map1}</span></td>
		</tr>
		<tr onclick="location.href='stock?priceIdx=1&postIdx=0'">
			<td>5,000 - 10,000원</td>
			<td><span class="tag is-primary right_">${map2}</span></td>
		</tr>
		<tr onclick="location.href='stock?priceIdx=2&postIdx=0'">
			<td>10,000 - 20,000원</td>
			<td><span class="tag is-primary right_">${map3}</span></td>
		</tr>
		<tr onclick="location.href='stock?priceIdx=3&postIdx=0'">
			<td>20,000 - 50,000원</td>
			<td><span class="tag is-primary right_">${map4}</span></td>
		</tr>
		<tr onclick="location.href='stock?priceIdx=4&postIdx=0'">
			<td>50,000 - 100,000원</td>
			<td><span class="tag is-primary right_">${map5}</span></td>
		</tr>
		<tr onclick="location.href='stock?priceIdx=5&postIdx=0'">
			<td>100,000원 이상</td>
			<td><span class="tag is-primary right_">${map6}</span></td>
		</tr>
		</tbody>
	</table>

	<table class="table is-hoverable is-fullwidth">
		<thead>
		<tr>
			<th>실시간 인기글</th>
			<th><a class="right_" href="community" style="color: #00947E; font-size: small;">더보기</a></th>
		</tr>
		</thead>
		<tbody>
		<% for(int i=0; i<list.size(); i++){%>
		<tr onclick="location.href='post?board_num=<%= list.get(i).getBoard_num() %>'">
			<td colspan="2"><%= list.get(i).getTitle() %></td>
		</tr>
		<% }%>
		</tbody>
	</table>
</div>
</body>

</html>