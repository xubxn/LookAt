<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>새 글</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<script src="https://code.jquery.com/jquery-3.7.0.min.js"
  integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
  crossorigin="anonymous"></script>
	<!-- Bootstrap 4 CSS -->
 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	<!-- Flatly 테마 CSS -->
	<link rel="stylesheet" href="https://bootswatch.com/4/cerulean/bootstrap.min.css">     
	
</head>
<body>
	<div class="container" >
		<!-- page title -->
		<h2 class="mt-5 mb-4 text-center">전시회를 등록하세요</h2>
		<form action="write.do" method="post" enctype="multipart/form-data">
			<div class="mb-4">
				<label for="title" class="form-label">전시명</label>
				<input type="text" name="title" id="title" class="form-control"/>
				<c:if test="${errors.title }" ><span class="error">제목을 입력하세요</span></c:if>
			</div>
			<div class="mb-4">
				<label for="open_date" class="form-label">시작일</label>
				<input type="date" name="open_date" id="open_date" class="form-control"/><br/>
				<c:if test="${errors.open_date }">시작일을 입력하세요</c:if>
			</div>
			<div class="mb-4" >
				<label for="end_date" class="form-label">종료일</label>
				<input type="date" name="end_date" id="end_date" class="form-control"><br/>
			</div>
			<div class="mb-4" >
				<label for="place" class="form-label">장소</label>
				<input type="text" name="place" id="place" class="form-control"><br/>
				<c:if test="${errors.place}" ><span class="error">장소를 입력하세요</span></c:if>
			</div>
			<div class="mb-4" >
				<label for="thumbnail" class="form-label">포스터</label>
				<input type="file" name="thumbnail" id="thumbnail" class="form-control" accept="video/*,image/*" onchange="setThumbnail(event, 'previewContainer')"><br/>
				<c:if test="${errors.thumbnail}" ><span class="error">대표이미지를  첨부해주세요</span></c:if>
				<div id="previewContainer"></div><br>
			</div>
			<div class="mb-4" >
				<label for="details_img" class="form-label" >상세사진</label>
				<input type="file" name="details_img" id="details_img" class="form-control" accept="video/*,image/*" onchange="setThumbnail(event, 'previewContainer2')"><br/>
				<div id="previewContainer2"></div><br>
			</div>
			<div class="mb-4" >
				<label for="introduction" class="form-label">상세소개</label>
				<textarea name="introduction" id="introduction" class="form-control" rows="5" cols="30"></textarea>
			</div>
			<div class="mb-4" >
				<label for="price_adult" class="form-label">가격 - 성인</label>
				<input type="text" name="price_adult" id="price_adult" class="form-control"/>
			</div>
			<div class="mb-4" >
				<label for="price_student" class="form-label">가격 - 학생</label>
				<input type="text" name="price_student" id="price_student" class="form-control"/>
			</div>
			<div class="mb-4" >
				<label for="price_baby" class="form-label">가격 - 어린이</label>
				<input type="text" name="price_baby" id="price_baby" class="form-control" />
			</div>
			<div class="mb-4" >
				<label for="loc" class="form-label">지역</label>
				<input type="text" name="loc" id="loc" class="form-control"/>
			</div>
			<div class="mb-4" >
				<label for="details_place" class="form-label">상세주소</label>
				<input type="text" name="details_place" id="details_place" class="form-control"/>
			</div>
			<div style="text-align: center;">
				<button type="submit" class="btn btn-primary">등록</button> 
				<button type="reset" class="btn">취소</button>	
			</div>
			<br/><br/>
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