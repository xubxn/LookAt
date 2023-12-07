<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>수정</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Bootstrap 4 CSS -->
 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<!-- Flatly 테마 CSS -->
	<link rel="stylesheet" href="https://bootswatch.com/4/cerulean/bootstrap.min.css">
	     
</head>
<body>
<%@ include file="/view/header.jsp" %>
 <div class="container">
    <!-- page title -->
	<h2  class="mt-5 mb-4 text-center">수정</h2>
	<form action="modify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="exhibition_no" value="${modReq.exhibition_no}"/>
		<input type="hidden" name="price_no" value="${modReq.price_no}"/>
		<input type="hidden" name="loc_no" value="${modReq.loc_no}"/>

		<div class="mb-4">
		<label for="title" class="form-label">전시명</label>
				<input type="text" name="title" id="title" class="form-control" value="${modReq.title}"/>
				<c:if test="${errors.title }" ><span class="error">제목을 입력하세요</span></c:if>
 		</div>
		<div class="mb-4">
				<label for="open_date" class="form-label">시작일</label>
				<input type="date" name="open_date" id="open_date" class="form-control" value="${modReq.open_date}"/><br/>
				<c:if test="${errors.open_date }">제목을 입력하세요</c:if> 
			</div>
			<div class="mb-4" >
				<label for="end_date" class="form-label">종료일</label>
				<input type="date" name="end_date" id="end_date" class="form-control" value="${modReq.end_date}"><br/>
			</div>
			<div class="mb-4" >
				<label for="place" class="form-label">장소</label>
				<input type="text" name="place" id="place" class="form-control" value="${modReq.place}"><br/>
				<c:if test="${errors.place}" ><span class="error">장소를 입력하세요</span></c:if>
			</div>
			<div class="mb-4" >
				<label for="thumbnail" class="form-label">포스터</label>
				<input type="file" name="thumbnail" id="thumbnail" class="form-control" value="${modReq.thumbnail}" accept="video/*,image/*" onchange="setThumbnail(event, 'previewContainer')"><br/>
				<c:if test="${errors.thumbnail}" ><span class="error">대표이미지를  첨부해주세요</span></c:if>
				<div id="previewContainer"></div><br>
			</div>
			<div class="mb-4" >
				<label for="details_img" class="form-label">상세사진</label>
				<input type="file" name="details_img" id="details_img" class="form-control" value="${modReq.details_img}" accept="video/*,image/*" onchange="setThumbnail(event, 'previewContainer2')"><br/>
			</div>
			<div id="previewContainer2"></div><br>
			<div class="mb-4" >
				<label for="introduction" class="form-label">상세소개</label>
				<textarea name="introduction" id="introduction" class="form-control" rows="5" cols="30">${modReq.introduction}</textarea>
			</div>
			<div class="mb-4" >
				<label for="price_adult" class="form-label">가격 - 성인</label>
				<input type="text" name="price_adult" id="price_adult" class="form-control" value="${modReq.price_adult}"/>
			</div>
			<div class="mb-4" >
				<label for="price_student" class="form-label">가격 - 학생</label>
				<input type="text" name="price_student" id="price_student" class="form-control" value="${modReq.price_student}"/>
			</div>
			<div class="mb-4" >
				<label for="price_baby" class="form-label">가격 - 어린이)</label>
				<input type="text" name="price_baby" id="price_baby" class="form-control" value="${modReq.price_baby}"/>
			</div>
			<div class="mb-4" >
				<label for="loc" class="form-label">지역</label>
				<input type="text" name="loc" id="loc" class="form-control" value="${modReq.loc}"/>
			</div>
			<div class="mb-4" >
				<label for="details_place" class="form-label">상세주소</label>
				<input type="text" name="details_place" id="details_place" class="form-control" value="${modReq.details_place}"/>
			</div>
			<div style="text-align: center;">
			     <a href="list.do"><button type="button" class="btn btn-primary">목록보기</button></a> 
			     <button type="submit" class="btn btn-primary">수정하기</button>
			     <a href="delete.do?no=${modReq.exhibition_no}"><button type="button" class="btn btn-primary">삭제하기</button></a> 
			</div>
   </form>
 </div>
 
 <script>
function setThumbnail(event, containerId) {
    var reader = new FileReader();

    reader.onload = function(event) {
        var img = document.createElement("img");
        img.setAttribute("src", event.target.result);
        img.setAttribute("class", "col-lg-6");

        var imageContainer = document.querySelector("div#" + containerId);
        if (imageContainer.hasChildNodes()) {
            imageContainer.replaceChild(img, imageContainer.firstChild);
        } else {
            imageContainer.appendChild(img);
        }
    };

    reader.readAsDataURL(event.target.files[0]);
}
</script>
 	<%@ include file="../footer.jsp" %> 
 
</body>
</html>