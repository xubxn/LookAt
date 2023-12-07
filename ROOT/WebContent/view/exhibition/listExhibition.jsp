<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>전시회 목록</title>
	<!-- CSS -->
	<link rel="stylesheet" href="../../css/exhibition/listExhibition.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- Font -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@500&family=Noto+Sans+KR&display=swap" rel="stylesheet">

</head>
<body>
 <%@ include file="/view/header.jsp" %>
 <div class="wrap">
	<form action="search.do" method="post">
		<div class="container">
			<div id="textBox" class="item">
				<h1>내가 보고싶은</h1>
				<h1>전시회만</h1>
				<h1>보자!</h1><br/>
				<h3>전시회를 찾아보세요!</h3>
			</div>
				<div id="itemLoc" class="item">
				<div id="itemTitleLocDiv"><span id="itemTitleLoc">개최 장소</span></div>
				<div id="locations" class="">
					<input type="radio" name="location" id="radio_btn_1" class="btn" value="서울"/>
					<span class="lableMargin"><label for="radio_btn_1">서울</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location" id="radio_btn_2" type="radio" value="경기"/>
					<span class="lableMargin"><label  for="radio_btn_2">경기</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location" id="radio_btn_3" type="radio" value="인천"/>
					<span class="lableMargin"><label for="radio_btn_3">인천</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location" id="radio_btn_4" type="radio" value="강원"/>
					<span class="lableMargin"><label for="radio_btn_4">강원</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_5" type="radio" value="울산"/>
					<span class="lableMargin"><label for="radio_btn_5">울산</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_6" type="radio" value="부산"/>
					<span class="lableMargin"><label for="radio_btn_6">부산</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_7" type="radio" value="경남"/>
					<span class="lableMargin"><label for="radio_btn_7">경남</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_8" type="radio" value="대구"/>
					<span class="lableMargin"><label for="radio_btn_8">대구</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_9" type="radio" value="경북"/>
					<span class="lableMargin"><label for="radio_btn_9">경북</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_10" type="radio" value="대전"/>
					<span class="lableMargin"><label for="radio_btn_10">대전</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_11" type="radio" value="충남"/>
					<span class="lableMargin"><label for="radio_btn_11">충남</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_12" type="radio" value="충북"/>
					<span class="lableMargin"><label for="radio_btn_12">충북</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_13" type="radio" value="광주"/>
					<span class="lableMargin"><label for="radio_btn_13">광주</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_14" type="radio" value="전북"/>
					<span class="lableMargin"><label for="radio_btn_14">전북</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_15" type="radio" value="전남"/>
					<span class="lableMargin"><label for="radio_btn_15">전남</label></span>
				</div>
				<div id="locations" class="">
					<input class="btn" name="location"  id="radio_btn_16" type="radio" value="제주"/>
					<span class="lableMargin"><label for="radio_btn_16">제주</label></span>
				</div>
			</div>	
			<!-- 개최장소 div 끝 -->
			<div id="itemYearMonth"class="item">
				<div id="itemTitleMonthDiv"><span id="itemTitleMonth">개최 시기</span></div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_17" type="checkbox" value="1월">
					<span class="lableMargin"><label for="radio_btn_17">1월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_18" type="checkbox" value="2월">
					<span class="lableMargin"><label for="radio_btn_18">2월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_19" type="checkbox" value="3월">
					<span class="lableMargin"><label for="radio_btn_19">3월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_20" type="checkbox" value="4월">
					<span class="lableMargin"><label for="radio_btn_20">4월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_21" type="checkbox" value="5월">
					<span class="lableMargin"><label for="radio_btn_21">5월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_22" type="checkbox" value="6월">
					<span class="lableMargin"><label for="radio_btn_22">6월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_23" type="checkbox" value="7월">
					<span class="lableMargin"><label for="radio_btn_23">7월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_24" type="checkbox" value="8월">
					<span class="lableMargin"><label for="radio_btn_24">8월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_25" type="checkbox" value="9월">
					<span class="lableMargin"><label for="radio_btn_25">9월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_26" type="checkbox" value="10월">
					<span class="lableMargin"><label for="radio_btn_26">10월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_27" type="checkbox" value="11월">
					<span class="lableMargin"><label for="radio_btn_27">11월</label></span>
				</div>
				<div id="yearMonth" class="">
					<input class="btn" name="yearMonth" id="radio_btn_28" type="checkbox" value="12월">
					<span class="lableMargin"><label for="radio_btn_28">12월</label></span>
				</div>
			<!-- 개최시기 div 끝 -->	
			</div>	
		<!-- form container div 끝 -->
		</div>
		<div id="searchBtnDiv" >
		<button id="searchBtn" type="submit">SEARCH</button> 
		</div>	
	</form>


	<div id="totalContent">총 ${exhibitionPage.total}건</div>
	<div class="exhibitionContainer">
		<c:forEach var="item" items="${exhibitionPage.content}">
			<a href="read.do?no=${item.exhibition_no}">
				<div class="exhibitionItem" width="1280px">
					<img id="thumbnail" src="<%=request.getContextPath() %>/view/image/${item.thumbnail}" style="width: 300px; height:418px; onmouseover="onHover();"/>
				</div>
			</a>
		</c:forEach>
	</div>

	<div class="pagenation">
 		<c:if test="${exhibitionPage.startPage > 5}">
 			<a href="list.do?pageNo=${exhibitionPage.startPage-5}"> &lt;&lt;pre </a>
 		</c:if>
 	
 		<%-- <c:forEach></c:forEach>1 2 3 4 5 6 7 8 9 10 --%>
 		<%--for(int i = 1; i<=10; i++) ${syso(i)} --%>
	 		<c:forEach var="pNo" begin="${exhibitionPage.startPage}"  end="${exhibitionPage.endPage}" step="1">
					<a class="pno" href="list.do?pageNo=${pNo}">${pNo}</a> 
	 		</c:forEach>
 		<c:if test="${exhibitionPage.endPage < exhibitionPage.totalPages}">
 			<a href="list.do?pageNo=${exhibitionPage.startPage+5}"> next&gt;&gt; </a>
 		</c:if>
 	</div>
</div>
	<%@ include file="../footer.jsp" %> 

</body>
</html>