<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.qbang.stockpedia.domain.Stock" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스톡피디아</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<%  List<Stock> list = (List<Stock>) request.getAttribute("list"); %>
<div class="container">
	<table class="table table-hover">
    <thead>
      <tr>
        <th>종목 이름</th>
        <th>가격</th>
      </tr>
    </thead>
    <tbody>
    <% if(list != null) {
    	for(int i=0; i<list.size(); i++){ %>
	      <tr >
	      	<td><%= list.get(i).getName() %></td>
	        <td><%= list.get(i).getValue() %></td>
	      </tr>
    <% 	} 
    	}else{%>
		<tr>
			<td colspan="2">데이터 없음</td>
		</tr>
		<% }%>
    </tbody>
	</table>
</div>
</body>
</html>