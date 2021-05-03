<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.qbang.stockpedia.domain.Board" %>
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
<% List<Board> list = (List<Board>) request.getAttribute("list"); %>

<div class="container">
	<button type="button" onclick="location.href='reqWrite'" class="btn btn-outline-secondary">글쓰기</button>
    <table class="table table-hover">
    <thead>
      <tr>
        <th>글 이름</th>
        <th>글 내용</th>
        <th>등록시간</th>
      </tr>
    </thead>
    <tbody>
    <% if(list != null) {
    	for(int i=0; i<list.size(); i++){ %>
	      <tr onclick="location.href='content?board_num=<%= list.get(i).getBoard_num() %>'">
	      	<td><%= list.get(i).getTitle() %></td>
	        <td><%= list.get(i).getContent() %></td>
	        <td><%= list.get(i).getReg_date() %></td>
	      </tr>
    <% 	} 
    	}else{%>
		<tr>
			<td colspan="3">데이터 없음</td>
		</tr>
		<% }%>
    </tbody>
	</table>
</div>
</body>
</html>