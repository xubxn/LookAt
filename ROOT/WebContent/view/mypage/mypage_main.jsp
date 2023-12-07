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
<link rel="stylesheet" href="${cPath}/css/mypage.css"/>
 <style>
    .custom-border {
        border-radius: 10px;
    }
       
  .navlink a:link, a:visited {
	color: black;
  	text-decoration: none;
	}
	
	.image {
	box-shadow: 5px 5px 5px gray;
	}

</style>
<!-- Bootstrap 4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
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
			<a href="${cPath}/myReservation.do?${AUTH_USER.id}" class="navlink">예매 내역</a> <br/>
			<a href="${cPath}/myReview.do?member_id=${AUTH_USER.id}" class="navlink">마이 리뷰</a> <br/>
			 <br/><br/>
			<h4>내 정보</h4>
			<a href="${cPath}/myInfoModify.do?member_id=${AUTH_USER.id}" class="navlink">프로필 관리</a><br/>
			<a href="${cPath}/myQA.do" class="navlink">문의 내역</a><br/>
			<a href="${cPath}/myLeave.do?member_id=${AUTH_USER.id}" class="navlink">회원 탈퇴</a><br/>				
		</div>
		<div class="contents">
			<div id="myInfo" class="media border p-3 custom-border">
			    <img src="${cPath}/view/image/profil.png" alt="LookAt" class="mr-4 mt-2 rounded-circle" style="width:60px;">
		    <div class="media-body" style="margin : 10px 0px 0px 0px;">
		      <h5>${AUTH_USER.name}님</h5> 
		      <div>${member.member_email}</div>
		    </div>
			</div>
			<div>
			<div style="width: 420px; display:inline-block;">
			<h5><b>예정된 전시회</b></h5>
			<hr/>
			<div class="table-responsive" >
			<table class="table table-bordered-0 table01" id="afterContent" style="height: 300px">
			 <tbody>
			<c:if test="${!empty afterContent.reservation_no}">
			 	<tr>
			 		<td rowspan="4" style="border-top: 0px;"><img src="${cPath}/view/image/${afterContent.thumbnail}" style="width:210px; height: 270px;" class="image"/></td>	 		
			 		<td style="border-top: 0px; font-size: 20px; height:140px;" >${afterContent.title}</td>	 					
			 	</tr>
			 	<tr style="border-top: 0px;">
			 		<td style="border-top: 0px;">${afterContent.place}</td>	 		
			 	</tr>
			 	<tr style="border-top: 0px;">
			 		<td style="border-top: 0px;">날짜 : ${afterContent.going_date}</td>
			 	</tr>
			 </c:if>
			 <c:if test="${empty afterContent.reservation_no}">
			 	<tr>
			 		<td rowspan="4" style="border-top: 0px;">예약된 전시회가 없습니다.</td>
			 	</tr>
			 </c:if>
			  </tbody>
			</table> 
			</div> 
		</div>
			<div style="width: 420px; height : 400px; display:inline-block; margin-left: 10px;">
			<h5><b>최근에 본 전시회</b></h5>
			<hr/>
			<div class="table-responsive" >
			<table class="table table-bordered-0 table01" id="beforeContent" style="height: 300px"  >
			 <tbody>
			 <c:if test="${!empty beforeContent.reservation_no}">
			 	<tr>
			 		<td rowspan="4" style="border-top: 0px; "><img src="${cPath}/view/image/${beforeContent.thumbnail}" style="width:200px; height: 270px;" class="image"/></td>	 		
			 		<td style="border-top: 0px; font-size: 20px; height:140px;">${beforeContent.title}</td>	 					
			 	</tr>
			 	<tr style="border-top: 0px;">
			 		<td style="border-top: 0px;">${beforeContent.place}</td>	 		
			 	</tr>
			 	<tr style="border-top: 0px;">
			 		<td style="border-top: 0px;">날짜 : ${beforeContent.going_date}</td>
			 	</tr>
			 	<tr style="border-top: 0px;">
			 	<c:if test="${result}">
			 		<td style="border-top: 0px;"><a href="/exhibition/read.do?no=${beforeContent.exhibition_no}" class="btn btn-outline-dark">리뷰 작성</a></td>
			 	</c:if>
			 	</tr>
			 	</c:if>
			 	<c:if test="${empty beforeContent.reservation_no}">
			 	<tr>
			 		<td rowspan="4" style="border-top: 0px; ">최근에 본 전시회가 없습니다.</td>	 		
			 	</tr>
			 	</c:if>
			  </tbody>
			</table> 
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