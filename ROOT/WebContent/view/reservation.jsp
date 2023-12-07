<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>예매하기</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../css/style2.css"/>
  </head>
<body>  
<%@ include file="/view/header.jsp" %>  
   <div class="box">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<form id="reservationForm" action="/reserve.do" method="get">
	<input type="hidden" id="exhibition_no" name="exhibition_no" value="${param.no}"/>
	  <div class ="reservationMain">
	  	<div class ="summaryall">
	  	 <div class="summaryTop">
	  		<h2 class="exTitle">${res.title}</h2>
			<input type="hidden" name="price_no" id="price_no" value="${res.price_no}"/>
			<input type="hidden" name="restitle" id="restitle" value="${res.title}"/>
			<input type="hidden" name="memid" id="memid" value="${reservationid}"/>
	  		<div class="exTitleBottom"></div>
	     </div>
	     <div class="summaryBody">
	     	<div class="posterBox">
	     	  <div class="posterBoxTop">
	     	 	<img class="posterBoxImage" src="<%=request.getContextPath() %>/view/image/${res.thumbnail}" alt="전시회 제목">
	     	  </div>
	     	  <div class="posterBoxBottom">
	     	  <h3 class="exTitle2">상세정보</h3>
	     	  <img class="posterDetailImage" src="<%=request.getContextPath() %>/view/image/${res.details_img}" >
	     	  </div>
	     </div>
	     <div class= "introduction">
	     	<p class="introduction_detail" >${res.introduction}</p>
	     </div>
	  	</div>
	  	</div>
	     <div id="calendarForm"></div>
	     <section style="float: right; width: 307px;">
	     <div class="boxcontrol" >
			<div class="title2_select selectBox" style="display: block;">
			<p class="selectbox_title" style="display: block;">권종선택
			<button type="button" id="adult" class="btn-option title2_btn btn_number_239096" name="239096" value="${res.price_adult}">
			<span class="option_title">성인</span>
			<span class="title2_price">1인 입장권</span>
			<span>${res.price_adult}원</span></button>
			<button type="button" id="teenager" class="btn-option title2_btn btn_number_239097" name="239097" value="${res.price_student}">
			<span class="option_title">청소년</span>
			<span class="title2_price">1인 입장권</span>
			<span>${res.price_student}원</span></button>
			<button type="button" id="childern" class="btn-option title2_btn btn_number_239098"  name="239098" value="${res.price_baby}">
			<span class="option_title">어린이</span>
			<span class="title2_price">1인 입장권</span>
			<span>${res.price_baby}원</span></button></p>
	       </div>
	    	<div class="num_select" style="display:none">
	    		<p class="title">수량선택</p>
	    			<div class="select_list">
	    				<div class="select_item" id="239096">
	    					<div class="select_name" style="float:left">성인 1인 입장권</div>
							<input type="hidden" name="cate_title[]" class="cate_title" value="성인 1인 입장권">
	    					<div style="float:right; display: inline-block;">
	    					<a href="#item_close" class="close" data-store="222528">
	    					<span class="remove_ticket1" style="font-size:14px; border:1px solid #888; border-radius:5px; width:16px; padding:0 6px; color:#fff; 
	    					background:#888;">X</span></a>
	    					</div>
	    					<div style="clear:both"></div>
	    					<div class="price_wrap">
	    						<div class="quantity">
	    							<button type="button" class="remove_ticket2" value="${res.price_adult}">
	    								<img src="../image/nus.png" width="20" height="20">
	    							</button>
	    							<span class="select_quanatity" id="quan">0</span>
	    							<button type="button" class="add_ticket" value="${res.price_adult}">
	    							<img src="../image/plus.png" width="20" height="20">
	    							</button>
	    							<p class="price">${res.price_adult}원</p>
	    							<input type="hidden" name="total_adult" id="total_adult" class="item_price" value="${res.price_adult}">
	    						</div>
	    					</div>
	    				</div>    		
	    				<div class="select_item" id="239097">
	    					<div class="select_name" style="float:left">청소년 1인 입장권</div>
							<input type="hidden" name="cate_title[]" class="cate_title" value="청소년 1인 입장권">
	    					<div style="float:right; display: inline-block;">
	    					<a href="#item_close" class="close" data-store="222528">
	    					<span class="remove_ticket1" style="font-size:14px; border:1px solid #888; border-radius:5px; width:16px; padding:0 6px; color:#fff; 
	    					background:#888;">X</span></a>
	    					</div>
	    					<div style="clear:both;"></div>
	    					<div class="price_wrap">
	    						<div class="quantity">
	    							<button type="button" class="remove_ticket2" value="${res.price_student}">
	    								<img src="../image/nus.png" width="20" height="20">
	    							</button>
	    							<span class="select_quanatity" id="quan2">0</span>
	    							<button type="button" class="add_ticket" value="${res.price_student}">
	    							<img src="../image/plus.png"  width="20" height="20">
	    							</button>
	    						  <p class="price">${res.price_student}원</p>
	    							<input type="hidden" name="total_student" id="total_student" class="item_price" value="${res.price_student}">
	    						</div>
	    					</div>
	    				</div>
	    				<div class="select_item" id="239098">
	    					<div class="select_name" style="float:left">어린이 1인 입장권</div>
							<input type="hidden" name="cate_title[]" class="cate_title" value="어린이 1인 입장권">
	    					<div style="float:right; display: inline-block;">
	    					<a href="#item_close" class="close" data-store="222528">
	    					<span class="remove_ticket1" style="font-size:14px; border:1px solid #888; border-radius:5px; width:16px; padding:0 6px; color:#fff; 
	    					background:#888;">X</span></a>
	    					</div>
	    					<div style="clear:both;"></div>
	    					<div class="price_wrap">
	    						<div class="quantity">
	    							<button type="button" class="remove_ticket2" value="${res.price_baby}">
	    								<img src="../image/nus.png" width="20" height="20">
	    							</button>
	    							<span class="select_quanatity" id="quan3">0</span>
	    							<button type="button" class="add_ticket" value="${res.price_baby}">
	    							<img src="../image/plus.png"  width="20" height="20">
	    							</button>
	    						  <p class="price">${res.price_baby}원</p>
	    							<input type="hidden" name="total_baby" id="total_baby" class="item_price" value="${res.price_baby}"/>
	    						</div>
	    					</div>
	    				</div>    		
	    			</div>
	    	</div>
	    	 <div class="total_wrap" style="display: flex; font-size:20px; font-weight: bold; color: #333333;">
	    	  <p class="title" >총 결제금액 :</p>
	    	  <p class="total_price">${res.price_adult + res.price_student + res.price_baby}
	    	  원</p>
	    	  <input type="hidden" id="total_price" value="${res.price_adult + res.price_student + res.price_baby}"/>
	    	 </div>
		</form>
		<form id="reserveForm" action="/reserve.do" method="post">
	    		<div class="submit_btn">	
	    	  <input class="button1" type="button" value="예매하기" id="btn_5"/>
				</div>
		</form>
	    <script src="<%=request.getContextPath() %>/js/script3.js" charset="UTF-8"></script>
			    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
			</section>
	       </div>
			</div>
			

</body>
</html>