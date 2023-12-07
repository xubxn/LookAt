<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u"   tagdir="/WEB-INF/tags"%>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>

 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title></title>
 <!-- Bootstrap 4 CSS -->
 <!-- Flatly 테마 CSS -->
 <link rel="stylesheet" href="../../css/readReview.css" />
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
 <!-- Navigation -->
<%--  <%@ include file="../navigation.jsp" %>     --%>      

<%--//Board board : 글번호,작성자id,제목,작성일,내용,이미지파일명
   Board board = boardService.getDetail(no);  
    request.setAttribute("board", board);  --%>
 <!-- 내용 -->   
 <div class="container">
<!-- page title -->
   
   
<!--     ★업로드기능있는 form은 반드시 method="post" enctype="multipart/form-data"★ -->
    <input type="hidden" name="review_no"   id="review_no" value="${review.review_no}"/>
    <input type="hidden" name="member_id"  id="member_id" value="${review_member_id}"/>
    <input type="hidden" id="pageNo" name="pageNo" value="${param.pageNo}">
   <table class="table table-borderless">
    <tbody>
     <tr>
      <th scope="row"></th>
      <td>${review.member_id}</td>
     </tr>



     <tr>
      <th scope="row"></th>
       <c:if test="${not empty review.review_img && review.review_img!=null}">
      
      <td>
         <input type="hidden" name="originalFileName" id="originalFileName" value="${review.review_img}"/>
            <img src="${cPath}/review/download.do?review_no=${review.review_no}&review_img=${review.review_img}" style="width:600px; height:auto;">
         </td>
     </c:if>
     
     </tr>
    
    <%--  <tr>
      <th scope="row" style="width:15%;">글번호</th>
      <td>${review.review_no}</td>
     </tr> --%>
     <tr>
      <th scope="row"><label for="title"></label></th>
      <td>${review.review_title}</td>
     </tr>
     <tr>
      <th scope="row"></th>
      <td><fmt:formatDate value="${review.review_date}"   pattern="yyyy년 M월 d일" /></td>
     </tr>
     <tr>
      <th scope="row"><label for="content"></label></th>
      <td>${review.details_review}</td>
     </tr>
     <%-- 첨부이미지파일이 존재하면 아래 tr이 보여진다 --%>
    </tbody>   
   </table>
   <!-- button --> 
   <!-- d-flex:한개의 row를 block레벨로 차지 
      flex-start:왼쪽정렬(기본)/ flex-end:오른쪽정렬 / flex-center:가운데정렬
      justify-content-end : 오른쪽정렬-->
    <div class="putter">
    <div class="d-flex justify-content-end">
    <c:set var="pageNo" value="${empty param.pageNo?1:param.pageNo}" /> 
     <a href="${cPath}/reviewList.do?pageNo=${pageNo}" class="btn btn-outline-dark">목록보기</a>
  
     <c:if test="${AUTH_USER.id==review.member_id}">
     <a href="${cPath}/reviewModify.do?review_no=${review.review_no}" class="btn btn-outline-dark">글 수정</a>
     </c:if>
        
    <c:if test="${AUTH_USER.id eq review.member_id}">
      <a href="${cPath}/reviewDelete.do?no=${review.review_no}" class="btn btn-outline-dark">게시글삭제(del)</a>
    </c:if>
   </div>
   </div>
   </div>
   

<%-- 댓글목록 시작 --%>
      <table class="table1" id="commenttable" style="width:700px;">
          <thead>
              <tr>
                  <th scope="col">작성자</th>
                  <th scope="col">댓글 내용</th>
                  <th scope="col">작성일</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
              </tr>
          </thead>
          <tbody>
              <c:choose>
                  <c:when test="${not empty comment}">
                      <c:forEach var="comment" items="${comment}">
                          <tr>
                              <td>${comment.member_id}</td>
                              <td>${comment.details_comment}</td>
                              <td><fmt:formatDate value="${comment.comment_date}" pattern="yyyy-MM-dd" /></td>
                              <td>
                                  <c:if test="${comment.member_id == sessionScope.AUTH_USER.id}">
                                      <button type="button" class="btn btn-sm btn-primary" 
                                               data-toggle="modal" data-target="#editModal" 
                                               data-review_comment_no="${comment.review_comment_no}" 
                                               data-review_no="${comment.review_no}" 
                                               data-member_id="${comment.member_id}" 
                                               data-details_comment="${comment.details_comment}">수정</button>
                                  </c:if>
                              </td>
                              <td>
                                  
                                  <c:if test="${comment.member_id == sessionScope.AUTH_USER.id}">
                                      <button type="button" class="btn btn-sm btn-danger" id="commentDelBtn" onclick="deleteComment(${comment.review_comment_no})">삭제</button>
                                  </c:if>
                             
                              </td>
                        
                          </tr>
                      </c:forEach>
                  </c:when>
                  <c:otherwise>
                      <tr>
                      </tr>
                  </c:otherwise>
              </c:choose>
          </tbody>
      </table><%-- 댓글목록 출력 끝 --%>

        <%-- 댓글 작성 폼 --%>
        <div class="">
            <!-- <h4>새로운 댓글 작성</h4> -->
            <form id="commentForm" action="${cPath}/RCommentInsert.do" method="post">
                <input type="hidden" name="review_no" value="${review.review_no}">
                <div class="insert">
                    <label for="member_id">작성자&nbsp;&nbsp;</label>
                    <input type="text"  style="width:150px;" class="" id="member_id" name="member_id" value="${sessionScope.AUTH_USER.id}" required="required">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="content">댓글 내용&nbsp;&nbsp;</label>
                    <input type="text"   style="width:350px;" class="" id="details_comment" name="details_comment" required="required"></textarea>
                &nbsp;&nbsp;<button type="submit" class="btn btn-outline-dark" style="">댓글 작성</button>
                </div>
                <!-- <div class="form-group">
                </div> -->
            </form>
        </div>

    <!-- 수정 모달창 -->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">댓글 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="editForm" method="post">
                        <input type="hidden" name="review_comment_no" id="review_comment_no" />
                        <input type="hidden" name="review_no"   id="review_no" />
                        <div class="form-group">
                            <label for="Member">작성자</label>
                            <input type="text" class="form-control" id="editmember_id" name="member_id" required>
                        </div>
                        <div class="form-group">
                            <label for="editContent">댓글 내용</label>
                            <textarea class="form-control" id="editdetails_comment" name="editdetails_comment" required></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-primary" onclick="updateComment()">수정 완료</button>
                </div>
            </div>
        </div>
    </div><!-- 수정 모달 끝 -->

<script>
    // 수정 모달이 나타날 때 정보를 설정}
    $("#editModal").on("show.bs.modal", function (event) {
        var button = $(event.relatedTarget); 
        var review_comment_no = button.data('review_comment_no'); // data-A_reply_no속성의 값
        var review_no   = button.data('review_no'); 
        var member_id  = button.data('member_id');
        var details_comment  = button.data('details_comment'); 

        // Modal창 내용 출력
        var modal = $(this);
        modal.find('#review_comment_no').val(review_comment_no);
        modal.find('#review_no').val(review_no);
        modal.find('#editmember_id').val(member_id);
        modal.find('#editdetails_comment').val(details_comment);
    
    });

    //모달창에서 수정완료 버튼 클릭시 호출
    function updateComment() {
        // 수정된 댓글 정보 가져오기
        var review_comment_no = $('#review_comment_no').val();
        var review_no = $('#review_no').val();
        var details_comment = $('#editdetails_comment').val(); //수정된 댓글내용
        var member_id = $('#editmember_id').val();
        //수정처리
        $.ajax({
            type: 'POST',
            url: '${cPath}/RCommentModify.do',
            data: { //서버로 전송되는 data=>서버입장에서는 파라미터안에 담겨진 data
               review_comment_no:review_comment_no,
               review_no:review_no,
               member_id:member_id,
               details_comment:details_comment
            },
            dataType: 'text',
            success: function(response) {
            if(response==="success"){  //수정성공
               location.reload();
            }else{ //수정실패시
               alert("댓글 수정이 실패되었습니다.");
            
            }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // 오류 발생 시 처리
                console.log('Error:', textStatus, errorThrown);
                alert('댓글 수정에 오류가 발생했습니다.');
            }
        });
    }
    function deleteComment(review_comment_no) {
        var Review_comment_no = $('#review_comment_no').val();
          alert(review_comment_no);
          var url = '${cPath}/RCommentDelete.do';
          var params = {
                Review_comment_no:review_comment_no
          };
          // AJAX 요청 보내기
          $.ajax({
              type: 'POST',
              url: url,
              data: params,
              dataType: 'text',
              success: function(response) {
                      alert("댓글을 삭제 했습니다.");
                      location.reload();
              },
              error: function(jqXHR, textStatus, errorThrown) {
                  // 오류 발생 시 처리
                  console.log('Error:', textStatus, errorThrown);
                  alert('댓글 삭제에 오류가 발생했습니다.');
                  location.reload();

              }
          });
      }  
    
</script>
   
<%@ include file="../bootstrap4js.jsp" %> 
</body>
</html>


            










