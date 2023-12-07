<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
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
<link rel="stylesheet" href="../../css/mypage.css"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="../../js/jquery-3.7.0.min.js"></script>
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
			<a href="${cPath}/myReservation.do?${AUTH_USER.id}" class="">예매 내역</a> <br/>
			<a href="${cPath}/myReview.do?member_id=${AUTH_USER.id}" class="">마이 리뷰</a> <br/>
			 <br/><br/>
			<h4>내 정보</h4>
			<a href="${cPath}/myInfoModify.do?member_id=${AUTH_USER.id}" class="">프로필 관리</a><br/>
			<a href="${cPath}/myQA.do?member_id=${AUTH_USER.id}" class="">문의 내역</a><br/>			
			<a href="${cPath}/myLeave.do?member_id=${AUTH_USER.id}" class="">회원 탈퇴</a><br/>
		</div>
		<div class="contents">
			<div id="myInfo" class="media border p-3">
			    <img src="${cPath}/view/image/profil.png" alt="John Doe" class="mr-3 mt-3 rounded-circle" style="width:60px;">
		    <div class="media-body">
		      <h4>${AUTH_USER.name}님</h4> 
		      <p>메일 주소</p> 
		    </div>
			</div>
			<div>
			<h6>1:1 문의</h6>
			<hr/>
			<div class="table-responsive" >
			<table class="table table-hover" >
			<tr style="text-align: center;">
			<td>글 번호</td>
			<td>글 제목</td>
			<td>작성자</td>
			<td>작성일</td>
			</tr>
	<c:if test="${questionPage.hasNoQuestion()}">
		<tr>
		<td colspan="4">문의 내역이 없습니다.</td>
		</tr>
	</c:if>
		<c:forEach var="question" items="${questionPage.content}">
		<tr>
		<td style="text-align: center;">${question.QA_no}</td>
		<td>
		<a href="Qread.do?QA_no=${question.QA_no}&pageNo=${questionPage.currentPage}" style="text-align: left;">
		<c:out value="${question.q_title}"/>
		</a>
		</td>
		<td style="text-align: center;">${question.member_id}</td>
		<td style="text-align: center;">${question.q_date}</td>
		</tr>
		</c:forEach>
		</table>
		<div style="text-align: right;">
		<c:if test="${questionPage.startPage>5}">
		<a href="${cPath}/myQA.do?pageNo=${questionPage.startPage-5}" class="btn btn-outline-dark">이전</a>
		</c:if>
		<c:forEach var="pNo" begin="${questionPage.startPage}" end="${questionPage.endPage}">
		<a href="${cPath}/myQA.do?pageNo=${pNo}" class="btn btn-outline-dark">${pNo}</a>
		</c:forEach>
		<c:if test="${questionPage.endPage<questionPage.totalPages}">
		<a href="${cPath}/myQA.do?pageNo=${questionPage.startPage+5}" class="btn btn-outline-dark">다음</a>
		</c:if>
		</div>
			</div> 
		</div>
		</div>
	</div>
	<%@ include file="../footer.jsp" %> 
	<!-- Bootstrap 4 JS -->
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>