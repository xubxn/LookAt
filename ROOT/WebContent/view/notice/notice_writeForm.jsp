<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>공지사항 작성</title>
<!--google fonts  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<style type="text/css">

 .custom-width {
 width: 700px;
 height: 500px;
 }
 
</style>
<link rel="stylesheet" href="../../css/notice/notice_writeForm.css"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
 <%@ include file="/view/header.jsp" %>
<div id="outer">
	<div id="wrap">
		<div id="board_area">
			<div id="board_header">
				<h3 class="mt-5 mb-4">공지사항</h3>
			</div>
			<form id="notice_write" action="noticeWrite.do" method="post">
			   <div class="container">
	      <input type=text class="form-control custom-width"  placeholder="공지 제목" name="n_title" id ="n_title"  >
	      <c:if test="${errors.n_title}">
	      <script>
	      		alert("제목을 입력하세요.")
	      </script></c:if>
	            <textarea class="form-control h-25 custom-width" rows="25" placeholder="공지 내용" name="n_details" id ="n_details" ></textarea>
	      		<c:if test="${errors.n_details}">
	      		<script>
	      		alert("내용을 입력하세요.")
	      		</script></c:if>
			</div>
			<div class="button">
	         <button type="submit"  id="subBtn" name="subBtn" >입력</button>
	        </div>
	         </form>
		</div>
	</div>
</div>
<%@ include file="../footer.jsp" %> 
<!-- Bootstrap 4 JS -->
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>