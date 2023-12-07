<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../../css/notice/notice_detail.css"/>
<title>공지사항</title>
<!--google fonts  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#listBtn").on("click", function(){
		var url_href = window.location.href;
		var url = new URL(url_href);
		var pageNo = url.searchParams.get("pageNo");
		location.href="http://localhost/noticeList.do?pageNo="+pageNo;		
	})
})
</script>
</head>
<body>
 <%@ include file="/view/header.jsp" %>
<div class="container">
<div id="outer">
	<div id="wrap">
		<div id="board_area">
			<div id="board_header">
				<h3 class="mt-5 mb-4">공지사항</h3>
			</div>
 			<form id = "deleteForm" action ="/noticeDelete.do?no=${noticeData.notice_no}&pageNo=${param.pageNo}" method="post"> 
				<table>
					<tr>
						<td class="title"><u:pre value="${noticeData.n_title}"></u:pre></td>
						<td class="date"><fmt:formatDate value="${noticeData.n_date}" pattern="yyyy.MM.dd"/></td>
					</tr>
					<tr>
						<td class="content" colspan="2"><u:pre value="${noticeData.n_details}"></u:pre></td>
					</tr>
				</table>
				<c:set var="pageNo" value="${empty param.pageNo? '1' : param.pageNo}"></c:set>
	 			<div class="board_footer">
 	 			<button type="button" id="listBtn" >목록보기</button>
		<c:if test="${AUTH_USER.id eq 'admin'}">
				<button type="button" id="updateBtn" onclick="location.href='noticeModify.do?no=${noticeData.notice_no}&pageNo=${param.pageNo}'" >게시글 수정</button>
				<button type="submit" id="deleteBtn" >게시글 삭제</button>
		</c:if>		
	 			</div>
	 			</form>
		</div>
	</div>
</div>
</div>
<%@ include file="../footer.jsp" %> 
	<!-- Bootstrap 4 JS -->
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>