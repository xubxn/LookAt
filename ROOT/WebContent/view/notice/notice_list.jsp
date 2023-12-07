<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../../css/notice/notice_list.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항</title>
<!--google fonts  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
 <%@ include file="/view/header.jsp" %>
<div class="container">
<div id="outer">
	<div id="wrap">
		<div id="board_area">
			<div id="board_header">
				<h3 class="mt-5 mb-4">공지사항</h3>
			</div>
	
		<form id ="notice_Form'" action="/noticeWrite.do" method="get">
		<table class="table table-hover">
		  <thead>
			<tr>
				<th scope="col" class="no">번호</th>
				<th scope="col" class="title">제목</th>
				<th scope="col" class="date">작성날짜</th>
			</tr>
		  </thead>
		  <tbody>
	 		 <c:forEach var="notice" items="${noticePage.content}"> 
				<tr>
					<td scope="col" class="no" >${notice.notice_no}</td>
					<td scope="col" class="title"><a href="noticeRead.do?no=${notice.notice_no}&pageNo=${nowPage}">${notice.n_title}</a></td>
					<td scope="col" class="date">${notice.n_date}</td>
				</tr>
			 </c:forEach>	
			</tbody>	
		</table>
		<div align="right">
		<c:if test="${AUTH_USER.getId() eq 'admin'}">
			<input type="submit" value="글쓰기" id="subBtn" name="subBtn" class="btn btn-light"/>
		</c:if>
		</div>
			</form>
		</div>
	<nav aria-label="Page navigation" >
		<ul class="pagination justify-content-center" style="margin:0 0">
		<c:if test="${noticePage.startPage>5}">
			<li class="page-item"><a class="page-link" href="noticeList.do?pageNo=${noticePage.startPage-5}">pre</a></li>
		</c:if>
		<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}" step="1">
			<li class="page-item"><a class="page-link" href="noticeList.do?pageNo=${pNo}">${pNo}</a></li>
		</c:forEach>
		<c:if test="${noticePage.endPage<noticePage.totalPages}">
			<li class="page-item"><a class="page-link" href="noticeList.do?pageNo=${noticePage.startPage+5}">next</a></li>
		</c:if>
		</ul>
	</nav>	
	</div>
</div>
</div>
<%@ include file="../footer.jsp" %> 
	<!-- Bootstrap 4 JS -->
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>