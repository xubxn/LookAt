<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>my Page</title>
<!--google fonts  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- Bootstrap 4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="../../css/mypage.css"/>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style type="text/css">
table {
width: 700px;
}
tr, td {
padding-bottom: 30px;
margin-bottom: 15px;
}

.image {
box-shadow: 5px 5px 5px gray;
}

</style>
</head>
<body>
 <%@ include file="/view/header.jsp" %>
	<div id="container" class="container">
		<div id="header" class="header">
			<!-- page title -->
			<h1 class="mt-5 mb-4 text-left">MyPage</h1>
		</div>
		<div class="left-sidebar">
			<h4>나의 전시회</h4><br/>
			<a href="<%=request.getContextPath() %>/myReservation.do?${AUTH_USER.id}" class="">예매 내역</a> <br/>
			<a href="<%=request.getContextPath() %>/myReview.do?member_id=${AUTH_USER.id}" class="">마이 리뷰</a> <br/>
			 <br/><br/>
			<h4>내 정보</h4>
			<a href="<%=request.getContextPath() %>/myInfoModify.do?member_id=${AUTH_USER.id}" class="">프로필 관리</a><br/>
			<a href="<%=request.getContextPath() %>/myQA.do?member_id=${AUTH_USER.id}" class="">문의 내역</a><br/>			
			<a href="<%=request.getContextPath() %>/myLeave.do?member_id=${AUTH_USER.id}" class="">회원 탈퇴</a><br/>
		</div>
		<div class="contents">
			<div>
			<h6>나의 리뷰</h6>
			<hr/>
			<div class="table-responsive" >
			<table  id="reservation">
			 <tbody>
			<c:if test="${empty myReviewPage.content}">
			 	<tr>
			 		<td>작성한 리뷰가 없습니다</td>
			 	</tr>
			</c:if>
			  <c:forEach var="myReview" items="${myReviewPage.content}"> 			 
			 	<tr>
			 		<td rowspan="2" class="thumbnail" style="width: 350px; height: 333px;"><img src="<%=request.getContextPath()%>/view/image/${myReview.thumbnail}" style="width:250px; height: 333px;" class ="image"/></td>	 		
			 		<td style="font-size: 25px;" colspan="2" ><b>${myReview.title}</b></td>	 					
			 	</tr>
			 	<tr>
			 		<td><img src="<%=request.getContextPath()%>/view/image/${myReview.review_img}" style="width:200px; height: 250px;" class ="image"/></td>
			 		<td><a href="<%=request.getContextPath()%>/reviewRead.do?review_no=${myReview.review_no}"><img src="<%=request.getContextPath()%>/view/image/상세보기버튼.png" style="width: 130px; padding-top: 210px; float: right; " /></a></td>
			 	</tr>
			  </c:forEach> 
			  </tbody>
			</table> 			
			</div>
			<div align="left">
		<c:if test="${myReviewPage.startPage>1}">
			<a href="myReview.do?pageNo=${myReviewPage.startPage-1}"><img src="<%=request.getContextPath()%>/view/image/arrow.png" style="transform: scaleX(-1); width: 20px;"/></a>
		</c:if>
		</div>
		<div align="right">
		<c:if test="${myReviewPage.endPage<myReviewPage.totalPages}">
			<a href="myReview.do?pageNo=${myReviewPage.startPage+1}"><img src="<%=request.getContextPath()%>/view/image/arrow.png" style="width: 20px;"/></a>
		</c:if> 
		</div>
		</div>
		</div>
	</div>
	<%@ include file="../footer.jsp" %> 
		<!-- Bootstrap 4 JS -->
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>