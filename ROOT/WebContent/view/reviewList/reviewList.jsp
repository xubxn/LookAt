<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%> --%>
<%@page import="review.dao.reviewDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="../reviewList.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700;900&family=Raleway:wght@600;700;800;900&family=Roboto:wght@300;400;500;700;900&display=swap" rel="
sheet">
<link href="../../css/reviewList.css" rel="stylesheet" >
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




</head>




<body>
 <%-- reviewPage : ${reviewPage} --%>


<%-- 
<%
boolean resuslt=(Boolean)request.getAttribute("errors");
if(resuslt){
	out.print("<span>id입</span>");
}
%>

<%
boolean resuslt1=(Boolean)request.getAttribute("errors");
if(resuslt1){%>
	<span>dddd</span>
<% }  %> --%>
<%@ include file="/view/header.jsp" %>


<form action="/reviewList.do" method="post" enctype="multipart/form-data">
<!-- 
<div class="Menu">

	<div class="topMenu">
		<div class="hambergerBtn">
		<div class="mobile_btn">
	<input type="checkbox" id="hamburger" />
	<label for="hamburger">
	  <span></span>
	  <span></span>
	  <span></span>
	</label>
	
	
	<div class="sidebar">
    <h2 style="text-align: center; position: relative; top: 75px;"><a href="#"></a></h2>
    <hr style="position: relative; top:120px; border: solid 1px black;">
    <ul class="nav_mobile">
      <li><a href="#">LOGIN</a></li>
      <li><a href="#">SEARCH</a></li>
      <li><a href="#">MEET OTHERS</a></li>
      <li><a href="#">MY EXHIBITION</a></li>
      <li><a href="#">MY PAGE</a></li>
      <li><a href="#">Q & A</a></li>
      <li><a href="#">NOTICE</a></li>
    </ul>
	</div>
	</div>
		
		</div>
			
		<div class="logoBtn"></div>
		<div class="logoBtn2"></div>
		
		<div class="topMenuRight">
			<div class="searchbarBtn">
				<form>
					<input type="text" class="searchbar" name="searchbar">
				</form>
			</div>
			<div class="saveBtn"></div>
			<div class="mypageBtn"></div>
		</div>
	</div>
</div>
	

<div class="box2">
	<div class="headerBox">
		<h2 class="header">MEET OTHERS</h2>
	</div>

	</div>
	 -->
	
	
<%-- <c:forEach var="review"  items="${reviewPage.content}" >
  <tr>
  	<td>${review.review_no}</td>
  	read.do?no=상세하게보고싶은글번호&pageNo=현재페이지" 
  	<td><a href="reviewList.do?no=${review.number}&pageNo=${nowPage}">${review.review_title}</a></td>
  	<td>${review.member_id}</td>
  	<td>${review.review_date}</td>
  </tr>
</c:forEach> 내용출력 끝 --%>

<section>
<div class="box">
 <div id="columns">
<c:forEach var="review"  items="${reviewPage.content}" varStatus="loop">
  <figure>
    <div id="contents"><img src="${cPath}/review/download.do?review_no=${review.review_no}&review_img=${review.review_img}" 
	   			 class="preview" data-index="${loop.index}" style="width:350px"/></div>
 
 	<div class="popup"></div>
       <div class="popmenu">
       	<div class="userpng"><img src="../img/user.png" ></div>
        <div class="userId">${review.member_id}</div>
        <%-- <div class="date">${review.review_date}</div> --%>
       	<div class="popimg">
       	<a href="reviewRead.do?review_no=${review.review_no}&pageNo=${nowPage}"><img src="${cPath}/review/download.do?review_no=${review.review_no}&review_img=${review.review_img}" style="height:520px; width:auto;"></a>
       	</div>
       	
       	
       	<div class="score" ><div class="starpoint_wrap" name="review_score" >
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
    <input type="radio" name="review_score" id="starpoint_1" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_2" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_3" class="star_radio" value="${review.review_score}"> 
    <input type="radio" name="review_score" id="starpoint_4" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_5" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_6" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_7" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_8" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_9" class="star_radio" value="${review.review_score}">
    <input type="radio" name="review_score" id="starpoint_10" class="star_radio" value="${review.review_score}">
    <span class="starpoint_bg"></span>
  </div>
</div></div>
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	
       	<div class="popEx">
	       	<div class="popex3" id="details_review">${review.details_review}</div>
	     
       </div>
       
       
              
       
       
       
       
       
       
       </div>
   
  
  
  
  
   
   
   
    <figcaption id="review_title">${review.review_title}</figcaption>
  </figure>
</c:forEach>
  </div>
</div>
</section>
 




<nav aria-label="Page navigation"> <!--  justify-content-center클래스는 가운데정렬  -->
	 <ul class="pagination" >
	  <%--<c:if>이용하여 노출여부가 달라진다  --%>
 <%-- 	  <c:if test="${reviewPage.startPage>5}">
	   <li class="page-item"><a class="page-link" href="list.do?pageNo=${reviewPage.startPage-5}" type=none;></a></li>
	  </c:if> 
	  
	  p653 43라인 <c:forEach></c:forEach>반복문이용
	  <c:forEach var="pNo"  begin="${reviewPage.startPage}" 
						    end="${reviewPage.endPage}"  step="1">
	   <li class="page-item"><a class="page-link" href="list.do?pageNo=${pNo}"></a></li>
	  </c:forEach>
	   
	  <c:if>이용하여 노출여부가 달라진다
	  <c:if test="${reviewPage.endPage< reviewPage.totalPages}"> 
	   <li class="page-item"><a class="page-link" href="list.do?pageNo=${reviewPage.startPage+5}"></a></li>
	  </c:if>  --%>
	 </ul> 	
    </nav>
</form>

<script>
/* document.getElementById("preview").onclick = function() {
    document.getElementById("popup").style.display="block";
    document.getElementById("popmenu").style.display="block";
}

document.getElementById("popup").onclick = function() {
    document.getElementById("popup").style.display="none";
    document.getElementById("popmenu").style.display="none";
}   
 */

//class가 "preview"인 모든 요소를 가져옵니다 (여러 개의 preview가 있다고 가정합니다)
 const previews = document.getElementsByClassName("preview");
 const popups = document.getElementsByClassName("popup");
 const popmenus = document.getElementsByClassName("popmenu");

 // 각각의 preview 요소에 이벤트 리스너를 연결합니다.
 for (let i = 0; i < previews.length; i++) {
     previews[i].onclick = function() {
         const index = this.getAttribute("data-index");
         popups[index].style.display = "block";
         popmenus[index].style.display = "block";
     }

     popups[i].onclick = function() {
         this.style.display = "none";
         popmenus[i].style.display = "none";
     }
 }
 
 
</script>


</body>
</html>