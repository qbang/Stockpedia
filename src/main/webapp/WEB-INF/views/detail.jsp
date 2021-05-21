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
</head>
<body>
<% List<Board> list = (List<Board>) request.getAttribute("list"); %>

<div class="container">
	<h1>${unick}님 환영합니다.</h1>
	<div class="row mb-3">
	  <div class="col-md-6 themed-grid-col">
	  	<div class="table-responsive">
	  		<div class="d-flex justify-content-between">
	  			<div>
	  				<h3>주식 정보</h3>
	  			</div>
	  		</div>
	        <table class="table table-hover">
	          <tbody>
	            <tr onclick="location.href='stock?idx=0'">
	              <td>5,000원 미만</td>
	              <td><span class="badge rounded-pill bg-secondary">${map1}</span></td>
	            </tr>
	            <tr onclick="location.href='stock?idx=1'">
	              <td>5,000 - 10,000원</td>
	              <td><span class="badge rounded-pill bg-secondary">${map2}</span></td>
	            </tr>
     	        <tr onclick="location.href='stock?idx=2'">
	              <td>10,000 - 20,000원</td>
	              <td><span class="badge rounded-pill bg-secondary">${map3}</span></td>
	            </tr>
	            <tr onclick="location.href='stock?idx=3'">
	              <td>20,000 - 50,000원</td>
	              <td><span class="badge rounded-pill bg-secondary">${map4}</span></td>
	            </tr>
     	        <tr onclick="location.href='stock?idx=4'">
	              <td>50,000 - 100,000원</td>
	              <td><span class="badge rounded-pill bg-secondary">${map5}</span></td>
	            </tr>
       	        <tr onclick="location.href='stock?idx=5'">
	              <td>100,000원 이상</td>
	              <td><span class="badge rounded-pill bg-secondary">${map6}</span></td>
	            </tr>
	          </tbody>
	        </table>
     	 </div>
	  </div>
	  
	  <div class="col-md-6 themed-grid-col">
	  	<div class="d-flex justify-content-between">
  			<div>
  				<h3>커뮤니티 인기글</h3>
  			</div>
  			<div>
  				<a href="community">더보기</a>
  			</div>
  		</div>
  		<table class="table table-hover">
	          <tbody>
	          <% for(int i=0; i<list.size(); i++){%>
          	      <tr onclick="location.href='content?board_num=<%= list.get(i).getBoard_num() %>'">
			      	<td><%= list.get(i).getTitle() %></td>
			      </tr>
	          <% }%>
	          </tbody>
	        </table>
	  </div>
	  
	</div>
</div>
</body>
</html>