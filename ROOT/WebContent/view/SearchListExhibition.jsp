<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전시회 목록</title>
<!--google fonts  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- CSS -->
<link rel="stylesheet" href="../../css/exhibition/listExhibitioncss2.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
 <%@ include file="/view/header.jsp" %>
<div class="title">
	<h1>EXHIBITION</h1>
	<div style="align-content: center;">
	<c:if test="${exhibitionPage.hasNoArticles()}">
	<div style="font-size: 20px; align-content: center;"><br/><br/>검색 결과가 없습니다.</div>
	</c:if>
	<c:if test="${!exhibitionPage.hasNoArticles()}">
		<div style="font-size: 20px; align-content: center;"><br/><br/><b>${exhibitionPage.total} 건의 검색결과가 있습니다. </b></div><br/>
	</c:if>
	</div>
</div>
<div class="wrap">
	<div class="exhibitionContainer">
		<c:forEach var="item" items="${exhibitionPage.content}">
			<a href="read.do?no=${item.exhibition_no}">
				<div class="item">
					<img src="<%=request.getContextPath() %>/view/image/${item.thumbnail}" style="width: 300px; height:418px;"/>
				</div>
			</a>
		</c:forEach>
	</div>

	<div class="pagenation">
 		<c:if test="${exhibitionPage.startPage > 5}">
 			<a href="<%=request.getContextPath() %>/searchExhibitionName.do?pageNo=${exhibitionPage.startPage-5}&searchbar=${name}"> &lt;&lt;pre </a>
 		</c:if>
 		<c:forEach var="pNo" begin="${exhibitionPage.startPage}"  end="${exhibitionPage.endPage}" step="1">
				<a href="<%=request.getContextPath() %>/searchExhibitionName.do?pageNo=${pNo}&&searchbar=${name}">${pNo}</a> 
 		</c:forEach>
 		
 		<c:if test="${exhibitionPage.endPage < exhibitionPage.totalPages}">
 			<a href="<%=request.getContextPath() %>/searchExhibitionName.do?pageNo=${exhibitionPage.startPage+5}&searchbar=${exhibitionPage.content.title}"> next&gt;&gt; </a>
 		</c:if>
 	</div>
</div>
	<%@ include file="./footer.jsp" %> 

</body>
</html>