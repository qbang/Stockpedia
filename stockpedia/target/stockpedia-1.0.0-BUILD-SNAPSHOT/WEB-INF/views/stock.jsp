<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.qbang.stockpedia.domain.Stock" %>

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
			margin-bottom: 30px;
		}
		.th_value{
			width: 100px;
		}
	</style>
</head>
<body>
<%  List<Stock> list = (List<Stock>) request.getAttribute("list"); %>
<div class="container">
	<table class="table is-hoverable is-fullwidth">
    <thead>
      <tr>
        <th>종목 이름</th>
        <th class="th_value">가격(원)</th>
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
	<br>
	<% String warning = (String) request.getAttribute("warning"); %>
	<% if (warning != null) { %>
	<div class="notification is-danger is-light">${warning}</div>
	<% } %>
	<div style="display: flex; justify-content: space-between" >
		<form action="stock" method="get">
	<%--		<div class="buttons">--%>
	<%--			<button class="button is-primary is-light bd-pagination-prev">이전</button>--%>
			<button class="button is-rounded">←</button>
	<%--		</div>--%>
			<input type="hidden" name="priceIdx" value="${priceIdx}"/>
			<input type="hidden" name="postIdx" value="${postIdx - 1}"/>
		</form>
		<form action="stock" method="get">
	<%--		<div class="buttons">--%>
	<%--			<button class="button is-primary bd-pagination-next">다음</button>--%>
	<%--		</div>--%>
			<button class="button is-rounded">→</button>
			<input type="hidden" name="priceIdx" value="${priceIdx}"/>
			<input type="hidden" name="postIdx" value="${postIdx + 1}"/>
		</form>
	</div>
</div>
</body>
</html>