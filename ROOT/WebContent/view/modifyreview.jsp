<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>
 <!-- Bootstrap 4 CSS -->
 <!-- Flatly 테마 CSS -->
 <!-- <link rel="stylesheet" href="https://bootswatch.com/4/flatly/bootstrap.min.css"> -->
 <!--<link rel="stylesheet" href="https://bootswatch.com/4/Cosmo/bootstrap.min.css"> --> 
    <link rel="stylesheet" href="https://bootswatch.com/4/flatly/bootstrap.min.css">
 <link rel="stylesheet" href="https://bootswatch.com/4/Cosmo/bootstrap.min.css"> 
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
</head>
<body>
<%@ include file="/view/header.jsp" %>
<%-- ModifyBoardController에의해서 아래와 같이 Model
//수정을 위해 세션에서 가져온 회원id, 글번호, db에서 가져온 제목과 내용 예정?????
//???ModifyRequest modReq=new ModifyRequest(user.getId(), no, oad.getTitle(), oad.getContent());
???request.setAttribute("modReq", modReq); --%>   

 <!-- Navigation -->
 <%-- <%@ include file="../navigation.jsp" %>     --%>

 <!-- 내용 -->
 <div class="container" style="position:absolute; top:250px; left:1000px; width: 500px; ">
    <!-- page title -->
   
   <form id="modifyForm" action="reviewModify.do" method="post" enctype="multipart/form-data">

    <div class="form-group">
      <label for="no">작성자</label>
      <input type="text" name="writerId" id="writerId" class="form-control" value="${review.member_id}" readonly="readonly"/>
     </div>
    <div class="form-group">
      <label for="no">글번호</label>
      <input type="text" name="review_no" id="no" class="form-control" value="${review.review_no}" readonly="readonly"/>
     </div>
    <div class="form-group">
       <label for="title">제목</label>
       <input type="text" name="title" id="title" class="form-control" value="${review.review_title}"/>
    </div>
    <div class="form-group">
       <label for="content">내용</label>
       <textarea rows="5" name="content" id="content" class="form-control">${review.details_review}</textarea>
    </div>
    <br/>
    <br/>
    <div>
        <input type="hidden" name="originalFileName" id="originalFileName" value="${review.review_img}"/>
         <img src="${cPath}/review/download.do?review_no=${review.review_no}&review_img=${review.review_img}" style="position:absolute; top:10px; left: -550px; height:620px; width:auto;">
    </div>
    <br/><br/>
    <div>
      <input type="file" name="imageFileName" id="imageFileName"  onchange="readURL(this);"  style="position:absolute; top:600px;"/>
        <img src="#" id="preview" style="height:150px; position:absolute; top:440px;"/> 
   </div>

   <!-- button -->
   <div class="d-flex justify-content-end">
     <button type="submit" class="btn btn-outline-dark" style="position:absolute; top:590px;">수정완료</button> 
   </div>
   </form>
 </div>
    
              
</body>
</html>












