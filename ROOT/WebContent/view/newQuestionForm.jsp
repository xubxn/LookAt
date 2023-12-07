<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 쓰기</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
textarea {

	width: 50%;
    height: 6.25em;
    border: 1px solid black;
    resize: none;
  }</style>
</head>
<%@ include file="/view/header.jsp" %>
<body>
<div class="container">
<div>
<c:if test="${empty AUTH_USER}">
<a href=login.do style="text-align: center;">[로그인]</a></c:if>
<c:if test="${!empty AUTH_USER}">
<form action="Qwrite.do" method="post" enctype="multipart/form-data">
<table class="table table-striped" style="text-align: center; border: 1px solid black">
<c:if test="${!empty AUTH_USER}">
작성자 : ${AUTH_USER.id} </c:if>
<input type=hidden name=member_id value= ${AUTH_USER.id}>
<% java.util.Date currentDate = new java.util.Date(); %>
<p>작성일시 :  <fmt:formatDate value="<%= currentDate %>" pattern="yyyy-MM-dd HH:mm:ss" /></p>
문의 유형<div class="form-control">
<c:if test="${errors.qnacategory}">문의 유형을 선택하세요</c:if>
		<input type=radio name=qnacategory value="0">배송
		<input type=radio name=qnacategory value="1">주문/결제
		<input type=radio name=qnacategory value="2">취소/환불
		<input type=radio name=qnacategory value="3">회원정보
		<input type=radio name=qnacategory value="4">주최문의
		<input type=radio name=qnacategory value="5">기타
		
	</div>
<p>제목<input type=text name=Q_title class="form-control" value="${param.Q_title}" placeholder="제목">
<c:if test="${errors.Q_title}">제목을 입력하세요.</c:if>
</p>
<p>내용<br>
<textarea class="form-control" placeholder="문의 내용" name=Q_details maxlength=5000 style="height:350px;">${param.Q_details}</textarea>
<c:if test="${errors.Q_details}">내용을 입력하세요.</c:if>
</p>
</table>
<div id="previewContainer"></div><br>
<input type="file" name=Q_plus_file id="Q_plus_file" accept="video/*,image/*" onchange="previewMedia(event)" />
<div id="fileErrorMessage" style="display: none; color: red;">파일을 선택해주세요.</div><br>
<div style="text-align: right;">

<button type=submit value=확인 class="btn btn-outline-dark">확인</button>
<button type=reset value=취소 class="btn btn-outline-dark">취소</button>
</div>
</div>

<script>
function previewMedia(event) {
    var input = event.target;
    var previewContainer = document.getElementById('previewContainer');
    var fileErrorMessage = document.getElementById('fileErrorMessage');
    previewContainer.innerHTML = ''; // 이전 미리보기 초기화

    if (input.files && input.files.length > 0) {
        fileErrorMessage.style.display = 'none'; // 에러 메시지 숨기기
        var reader = new FileReader();
        var mediaFile = input.files[0];

    if (mediaFile.type.startsWith('image/')) {
            reader.onload = function(e) {
                var image = document.createElement('img');
                image.src = e.target.result;
                image.style.maxWidth = '300px';
                image.style.maxHeight = '300px';
                previewContainer.appendChild(image);
            };
            reader.readAsDataURL(mediaFile);
        }

        var fileNamesContainer = document.getElementById('fileNames');
        fileNamesContainer.innerHTML = ''; // 파일명 초기화

        var fileNameElement = document.createElement('p');
        fileNameElement.textContent = mediaFile.name;
        fileNamesContainer.appendChild(fileNameElement);
    } else {
        fileErrorMessage.style.display = 'block'; // 에러 메시지 표시
        var fileNamesContainer = document.getElementById('fileNames');
        fileNamesContainer.innerHTML = ''; // 파일명 초기화
    }
}

    </script>
    </form>
    <!-- Bootstrap 4 JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXakfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz4fnFO9gybBvRLFyyN+kW/BQro3T8j6XI4lK7T7rM46_tC6Y1Bf/Dbdbp"
        crossorigin="anonymous"></script>
<scrip 	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP8zjCGMXP5R6nX6KZQJcdTd/ftMf6nH16Pz9JvqBabTTLNZQbVfaGnt"
        crossorigin="anonymous"></script>	
    </c:if>
    </div>
    	<%@ include file="./footer.jsp" %> 
    
</body>
</html>