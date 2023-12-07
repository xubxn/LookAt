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
<style type="text/css">
.error {
font-size: 0.8em;
color: red;
}
</style>
<!-- Bootstrap 4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="../../css/mypage.css"/>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
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
			<div>
			<h6>정보 수정</h6>
			<hr/>
			<form id="modifyForm" action="myInfoModify.do" method="post">
			<div class="form-group">
			<label for="member_name">이름</label>
			<input type="text" name="member_name" id="member_name" class="form-control" value="${member.member_name}" readonly="readonly"/>
			</div>
			<div class="form-group">
			<label for="member_id">아이디</label>
			<input type="text" name="member_id" id="member_id" class="form-control" value="${member.member_id}" readonly="readonly"/>
			</div>
			<div class="form-group">
			<label for="member_pw">비밀번호</label>
			<input type="password" name="member_pw" id="member_pw" class="form-control" />
			<c:if test="${errors.password}" ><span class="error">비밀번호를 입력하세요</span></c:if>
			</div>
			<div class="form-group">
			<label for="confirm_pw">비밀번호 확인</label>
			<input type="password" name="confirm_pw" id="confirm_pw" class="form-control" />
			<c:if test="${errors.confirmPassword}"><span class="error">확인용 비밀번호를 입력하세요</span></c:if>
			<c:if test="${errors.notMatch}" ><span class="error">비밀번호가 일치하지 않습니다</span></c:if>
			</div>
			<div class="form-group">
			<label for="member_tel">연락처</label>
			<input type="text" name="member_tel" id="member_tel" class="form-control"  value="${member.member_tel}" />
			<c:if test="${errors.tel}" ><span class="error">연락처를 입력하세요</span></c:if>
			</div>
			<div class="form-group">
			<label for="member_email">이메일</label>
			<input type="text" name="member_email" id="member_email" class="form-control"  value="${member.member_email}"/>
			<c:if test="${errors.email}" ><span class="error">이메일을 입력하세요</span></c:if>
			</div>
			<input type="submit" id ="submitBtn" class ="" value="정보 수정" />
			</form>			
			</div> 
		</div>
		</div>
	<%@ include file="../footer.jsp" %> 
	<!-- Bootstrap 4 JS -->
<%@ include file="../bootstrap4js.jsp" %> 	
</body>
</html>
