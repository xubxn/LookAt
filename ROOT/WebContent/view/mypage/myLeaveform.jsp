<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<a href="${cPath}/myReservation.do?member_id=${AUTH_USER.id}" class="">예매 내역</a> <br/>
			<a href="${cPath}/myReview.do?member_id=${AUTH_USER.id}" class="">마이 리뷰</a> <br/>
			 <br/><br/>
			<h4>내 정보</h4>
			<a href="${cPath}/myInfoModify.do?member_id=${AUTH_USER.id}" class="">프로필 관리</a><br/>
			<a href="${cPath}/myQA.do?member_id=${AUTH_USER.id}" class="">문의 내역</a><br/>			
			<a href="${cPath}/myLeave.do?member_id=${AUTH_USER.id}" class="">회원 탈퇴</a><br/>
		</div>
		<div class="contents">
			<form id="leaveForm" action="myLeave.do" method="post">
			<h6>회원 탈퇴</h6>
			<hr/>
			<div class="leaveterms" style="background-color: WhiteSmoke; width: 750px; margin-left: 60px;">
			<div class="form-group">
			<label for="terms">[홈페이지 회원 탈퇴 약관]</label>
			</div>
			<div class="form-group">
			이 약관은 홈페이지 회원 탈퇴에 관한 사항을 규정하는 것을 목적으로 합니다. 회원이 홈페이지에서 탈퇴하기 위해서는 본 약관에 동의하여야 합니다. 회원은 본 약관에 동의함으로써 회원 탈퇴를 진행하고, 탈퇴 후의 정보 처리에 동의한 것으로 간주됩니다.
			</div>
			<div class="form-group">
			회원 탈퇴 절차 및 방법 설명 <br/>
			1.1 회원 탈퇴는 로그인한 상태에서 '회원 탈퇴' 메뉴를 통해 신청할 수 있습니다. <br/>
			1.2 회원 탈퇴 시에는 본인 확인 절차를 거친 후 탈퇴 처리가 이루어집니다. <br/>
			<br/>
			개인정보 보호<br/>
			2.1 회원 탈퇴로 인해 처리되는 개인정보는 개인정보 처리 방침에 따라 처리되며, 해당 정보는 탈퇴 처리가 완료된 후 즉시 파기되거나 익명화 처리됩니다.<br/>
			<br/>
			탈퇴 후 정보 보존<br/>
			3.1 회원 탈퇴 후에도 일부 정보는 보존될 수 있습니다. 예를 들어, 서비스 이용 내역에 관한 정보는 관련 법령과 회사의 내부 방침에 따라 일정 기간 동안 보존됩니다.<br/>
			<br/>
			재가입 제한<br/>
			4.1 회원이 탈퇴한 이후, 일정 기간 동안 재가입이 제한될 수 있습니다. 재가입 제한 기간은 회사의 내부 방침에 따라 달라질 수 있습니다.<br/>
			<br/>
			약관 변경 가능성<br/>
			5.1 이 약관은 변경될 수 있습니다. 약관 변경 시 회원에게는 사전 공지가 이루어질 수 있으며, 변경된 약관은 공지된 날로부터 시행됩니다.<br/>
			<br/>
			기타 유의사항<br/>
			6.1 회원 탈퇴 이후, 탈퇴 시점까지 적립된 혜택 및 보유한 권리 등은 모두 소멸될 수 있습니다.<br/>
			6.2 회원은 탈퇴 이후 해당 홈페이지의 일체의 서비스 이용과 관련하여 어떠한 권리도 주장할 수 없습니다.<br/>
			6.3 회원은 자신의 책임 하에 탈퇴를 진행함을 이해하고 동의합니다.<br/>
			</div>
			<div class="form-group">
			위의 약관은 홈페이지 회원 탈퇴에 관한 사항을 정의한 것으로, 회원들은 본 약관에 동의함으로써 회원 탈퇴를 진행하게 됩니다. 회원은 본 약관을 충분히 이해하고 동의한 뒤 탈퇴 신청을 진행해야 합니다. 탈퇴 절차와 개인정보 처리 방침에 대한 자세한 사항은 홈페이지 내 '회원 탈퇴' 및 '개인정보 처리 방침' 섹션을 참고하시기 바랍니다.
			</div>
			</div>
			<div align="right" style="margin-right : 55px; "><input type="checkbox" name="agreeYN" id="agreeYN" value="ok" onclick="check()"/>약관 동의<br/>
			<hr/>
			<label for="member_id">아이디</label>
			<input type="text" name="member_id" id="member_id" class="" /><br/>
 			<c:if test="${errors.member_id}" ><span class="error">아이디를 입력하세요<br/></span></c:if>
			<label for="member_pw">비밀번호</label> 
			<input type="password" name="member_pw" id="member_pw" class="" /><br/>
			<c:if test="${errors.member_pw}" ><span class="error">비밀번호를 입력하세요<br/></span></c:if> 
 			<c:if test="${errors.idpwNOTmatch}" ><span class="error">아이디와 비밀번호가 일치하지 않습니다<br/></span></c:if> 
			<input type="submit" id ="submitBtn" class ="" value="탈퇴" onclick="check()"/>
			</div>
			</form>	
			</div>		
		</div>
		<script>
			function check(){
				let agreeYN = document.getElementById("agreeYN");
				let agreeYNChecked=false;
				
				if(agreeYN.checked){	
					agreeYNChecked=true;
				}				
				if(!agreeYN.checked){
					alert("약관에 동의해주세요.");
					return false;
				}				
				var con = confirm("정말... 정말 .... 정말로.... 탈퇴를 진행하시겠습니까...? 저희 사이트 진짜 좋아요 .... 허접하긴 해도 ... 좋은데 ㅠ... 찐으로 ... 탈퇴인가요 ㅠ.....");
				if(con==true){
					alert("한번만 다시 생각해보세요 ㅠㅠㅠㅠㅠㅠㅠㅠ 제발요 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
					alert("진짜 진짜 진짜 진짜 탈퇴이신가요 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ 안돼 ㅠㅠㅠㅠㅠ 가지마");
					alert("탈퇴를 진행할게요 ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ 너무 슬퍼요 ㅠㅠㅠㅠㅠㅠ 가지마 ㅠㅠㅠㅠㅠㅠㅠㅠ");
				}
				return true;
			}
			
			</script>			
<%@ include file="../footer.jsp" %> 
	<!-- Bootstrap 4 JS -->
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>
