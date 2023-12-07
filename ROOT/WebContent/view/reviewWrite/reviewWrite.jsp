<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="review.dao.reviewDAO"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSP 이미지 업로드 폼</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script> 
<link rel="stylesheet" href="../../css/reviewWrite.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700;900&family=Raleway:wght@600;700;800;900&family=Roboto:wght@300;400;500;700;900&display=swap" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
function readURL(obj){
	  if (obj.files && obj.files[0]) { //파일이 있드면
	         var reader = new FileReader(); //FileReader()객체생성
	         reader.onload = function (e) {
	        	 //id가 preview인 요소의 src속성값을 설정 =>img src속성값을 파일명으로 적용
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(obj.files[0]);
	  }
}

</script>

<script type="text/javascript">




</script>





</head>
<body>
<%-- <%@ include file="../navigation.jsp" %> --%>


 <div class="logo1" >
	<img src = "../img/LOGO.png">
 </div >
 
 <div class="white">
 
 </div>
 	  
 
	<form name="frm" id="frm" action="<%=request.getContextPath()%>/reviewWrite.do" method="post" enctype="multipart/form-data" >
	

 	
 	<input type="hidden" name="review_date"/>
	<input type="hidden" name="review_no"   id="review_no" value="${review.review_no}"/>

	

	<div class = "member">
		<label for="member_id" class="form-label"></label>
 		<input type="hidden" name="member_id" id="member_id" value="${AUTH_USER.id}"/>
 		
	</div>

	<div class = "exhibitonNo">
		<label for="exhibition_no" class="form-label"></label>
		<input type="hidden" name="exhibiton_no" id="exhibition_no" value="${param.no}"/>
	</div>
 
 
 <div class="littlebox">
 <p class="ptitle">제목</p>
 <p class="pcontent">내용</p>
 	<div class="title">
 		
 		<p class="titleWr">
 			<input type="text" class="text1" name="review_title" id="review_title" style="border:0 solid black; margin-left: 20px; margin-top:-13px;">
 		</p>
	</div>
 
	
	<div class="contents">
 		
 		<p class="contentsWr">
 			<textarea class="text2" name="details_review" id="content" style="border:0 solid black; margin-left: 20px; width: 530px; height:290px; word-wrap:break-word; "></textarea>
 		</p>
 	</div>
 </div>
 
 <div class="file_box">	
	  	<input type="file" name="review_img" id="review_img"  onchange="readURL(this);" />
	  	<img src="#" id="preview" style="height:480px; margin-left:-100px; margin-top:20px;"/> 
	  </div>
		
		

 	  <div>
	 	<button  type="submit" class="complete" onclick="submit();">작성완료</button>
	  </div> 

	
	
	
	
	
	
	
	
	
	
	<div class="starpoint_wrap" >
  <div class="starpoint_box">
    <label for="starpoint_1"  class="label_star" title="0.5"><span class="blind"></span></label>
    <label for="starpoint_2"  class="label_star" title="1"><span class="blind"></span></label>
    <label for="starpoint_3"  class="label_star" title="1.5"><span class="blind"></span></label>
    <label for="starpoint_4"  class="label_star" title="2"><span class="blind"></span></label>
    <label for="starpoint_5"  class="label_star" title="2.5"><span class="blind"></span></label>
    <label for="starpoint_6"  class="label_star" title="3"><span class="blind"></span></label>
    <label for="starpoint_7"  class="label_star" title="3.5"><span class="blind"></span></label>
    <label for="starpoint_8"  class="label_star" title="4"><span class="blind"></span></label>
    <label for="starpoint_9"  class="label_star" title="4.5"><span class="blind"></span></label>
    <label for="starpoint_10" class="label_star" title="5"><span class="blind"></span></label>
    <input type="radio" name="review_score" id="starpoint_1" class="star_radio" value="1">
    <input type="radio" name="review_score" id="starpoint_2" class="star_radio" value="2">
    <input type="radio" name="review_score" id="starpoint_3" class="star_radio" value="3"> 
    <input type="radio" name="review_score" id="starpoint_4" class="star_radio" value="4">
    <input type="radio" name="review_score" id="starpoint_5" class="star_radio" value="5">
    <input type="radio" name="review_score" id="starpoint_6" class="star_radio" value="6">
    <input type="radio" name="review_score" id="starpoint_7" class="star_radio" value="7">
    <input type="radio" name="review_score" id="starpoint_8" class="star_radio" value="8">
    <input type="radio" name="review_score" id="starpoint_9" class="star_radio" value="9">
    <input type="radio" name="review_score" id="starpoint_10" class="star_radio" value="10">
    <span class="starpoint_bg"></span>
  </div>
</div>
	</form>
	
	


	</body>
</html>















