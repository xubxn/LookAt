<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 목록</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <style>
H1{
margin:120px 0px 0px 0px;
}
a{
 color:black;
 }
 link{
 text-decoration:none;
 text-decoration:no-underline;
 
 }
</style>
 
</head>
<%@ include file="/view/header.jsp" %>
<body>

<div class="container">
<H1 style="text-align: center;" >1:1문의</H1><br>
<table class="table table-hover" >
<tr style="text-align: center; font-weight:600;">
<td>글 번호</td>
<td>글 제목</td>
<td>작성자</td>
<td>작성일</td>
</tr>
<c:if test="${QuestionPage.hasNoQuestion()}">
<tr>
<td colspan=4>게시글이 없습니다.</td>
</tr>
</c:if>

<c:forEach var="question" items="${QuestionPage.content}">
  <c:if test="${AUTH_USER.id==question.member_id||AUTH_USER.id.equals('admin')}">
<tr>
<td style="text-align: center;">${question.QA_no}</td>
<td>
<a href="Qread.do?QA_no=${question.QA_no}&pageNo=${QuestionPage.currentPage}" style="text-align: left;">
<c:out value="${question.q_title}"/>
</a>
</td>
<td style="text-align: center;">${question.member_id}</td>
<td style="text-align: center;">${question.q_date}</td>
</tr>
	</c:if>
</c:forEach>
<c:if test="${QuestionPage.hasQuestion()}">
<tr style="text-align: center;">
<td colspan=4>
<c:if test="${QuestionPage.startPage>5}">
<a href="Qlist.do?pageNo=${QuestionPage.startPage-5}" class="btn btn-outline-dark">이전</a>
</c:if>
<c:forEach var="pNo" begin="${QuestionPage.startPage}" end="${QuestionPage.endPage}">
<a href="Qlist.do?pageNo=${pNo}" class="btn btn-outline-dark">${pNo}</a>
</c:forEach>
<c:if test="${QuestionPage.endPage<QuestionPage.totalPages}">
<a href="Qlist.do?pageNo=${QuestionPage.startPage+5}" class="btn btn-outline-dark">다음</a>
</c:if>
</td>
</tr>
</c:if> 
</table>
<div align="right">
<a href="Qwrite.do" class="btn btn-outline-dark" >글쓰기</a>
</div>
<br>
</div>	
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXakfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz4fnFO9gybBvRLFyyN+kW/BQro3T8j6XI4lK7T7rM46_tC6Y1Bf/Dbdbp"
        crossorigin="anonymous"></script>
<script="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
        crossorigin="anonymous"></script>
	<%@ include file="./footer.jsp" %> 

</body>
</html>
