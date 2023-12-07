<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>예매결과 상세 조회</title>
    <!--google fonts  -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/reserveStyle.css" />
  </head>
  <body style="background-color: #fff;  min-height: 100vh;">  
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<input type="hidden" id="exhibition_no" name="exhibition_no" value="${param.no}"/>
  <div class ="reservationMain">
  	<div class ="summaryall">
  	 <div class="summaryTop">
  		<h2 class="exTitle">${myReservation.title}</h2>
  		<div class="exTitleBottom"></div>
     </div>
     <div class="summaryBody">
     	<div class="posterBox">
     	  <div class="posterBoxTop">
     	 	<img class="posterBoxImage" src="<%=request.getContextPath() %>/view/image/${myReservation.thumbnail}" alt="전시회 제목">
     	  </div>
     	  <div class="posterBoxBottom">
     	  <h3 class="exTitle2">상세정보</h3>
     	  <img class="posterDetailImage" src="<%=request.getContextPath() %>/view/image/${myReservation.details_img}" >
     	  </div>
     </div>
     <div class= "introduction">
     	<p class="introduction_detail" >${myReservation.introduction}</p>
     </div>
  	</div>
  	</div>
     <section style="float: right; width: 307px;">
     <div class="boxcontrol" >
     	<div class="buyDate" id="DateBuy">
     	<p>결제 날짜</p>
     	<p class="buyDate2">${myReservation.reser_date}</p>
     	</div>
     	<div class="regDate" id="DateReg">
     	<p>예약 날짜</p>
     	<p class="regDate2">${myReservation.going_date}</p>
     	<p></p>
     	</div>
		<div class="title2_select selectBox" style="display: block;">
		<p class="selectbox_title" style="display: block;">입장권 구매 현황
		<div id="adult" name="adult" >
		<span class="option_title">성인</span>
		<span class="title2_price">1인 입장권</span>
		<span>${myReservation.total_adult}매</span></div>
		<div id="teenager" name="teenager" >
		<span class="option_title">청소년</span>
		<span class="title2_price">1인 입장권</span>
		<span>${myReservation.total_student}매</span></div>
		<div  id="childern"  name="childern">
		<span class="option_title">어린이</span>
		<span class="title2_price">1인 입장권</span>
		<span>${myReservation.total_baby}매</span></div>
       </div>
    	 <div class="total_wrap" style="display: flex;">
    	  <p class="title" >총 결제금액 :</p>
    	  <p class="total_price">${myReservation.total} 원</p><br/>
    	 </div>
    		<div class="submit_btn">
    		<a href="<%=request.getContextPath() %>/myReservation.do?member_id=${AUTH_USER.id}" class="btn btn-outline-dark">예매 목록</a>
			</div>
		</div>
		</section>
       </div>
       
</body>
</html>