<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 내역 확인</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700;900&family=Raleway:wght@600;700;800;900&family=Roboto:wght@300;400;500;700;900&display=swap" rel="stylesheet">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
 <style>
 
table {
  border-collapse: collapse;
}

.container {
  width: 800px; 
  margin: 10% auto; 
}


</style>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<%@ include file="/view/header.jsp" %>
<body>
<div class="container" >
<h1 style="text-align: center;">1:1 문의</h1><br>
<table border=0   class="table table-border-bottom mt-1">
<tr>
<td colspan=1  class=left>글 번호 : ${question.QA_no}</td>
<td colspan=1 style="text-align: right;"  class=right>작성자 : ${question.member_id}</td>
</tr>
<tr>
<td class=left>문의 유형 : 
${question.q_no eq '0' ? '배송':''}
${question.q_no eq '1' ? '주문/결제':''}
${question.q_no eq '2' ? '취소/환불':''}
${question.q_no eq '3' ? '회원정보':''}
${question.q_no eq '4' ? '주최문의':''}
${question.q_no eq '5' ? '기타':''}
</td>
<td style="text-align: right;" class=right>작성일 : ${question.q_date}</td>
</tr>
<tr>
<td colspan=2>제목 : <u:pre value="${question.q_title}" /></td>
</tr>
<tr>
<td colspan=2 style="background-color:#ebebeb;"><u:pre value="${question.q_details}"/></td>
</tr>
<tr >
<c:if test="${not empty question.q_plus_file && question.q_plus_file!=null }">
<td colspan=2>첨부파일 </td>
<tr>
<td style="text-align: center;" colspan=2>
<img src="${request.getContextPath()}/Qdownload.do?QA_no=${question.QA_no}&Q_plus_file=${question.q_plus_file}"  style="width:500px;" name=Q_plus_file/></td>
</tr></c:if>
<input type="hidden" name="originalFileName" id="originalFileName" value="${question.q_plus_file}"/>
</tr>
</table>
<c:set var="pageNo" value="${empty param.pageNo?'1':param.pageNo}" />
<div class="row">
    <div class="col-md-6">
        <a href="Qlist.do?pageNo=${pageNo}" class="btn btn-outline-dark">목록</a>
    </div>
    <div class="col-md-6 text-right">
        <c:if test="${AUTH_USER.id==question.member_id||AUTH_USER.id.equals('admin')}">
            <a href="Qmodify.do?QA_no=${question.QA_no}" class="btn btn-outline-dark">수정</a>
            <a href="Qdelete.do?QA_no=${question.QA_no}" class="btn btn-outline-dark">삭제</a>
        </c:if>
    </div>
    <br>
    </div>
      <br>
      
<%-- 답글목록 시작 --%>
    <h3 class="mt-4">답글</h3>
      <table class="table mt-3" id="commentTable">
          <thead>
              <tr style="text-align: center;">
                  <th scope="col">답글번호</th>
                  <th scope="col">작성자</th>
                  <th scope="col">답글 내용</th>
                  <th scope="col">작성일</th>
              </tr>
          </thead>
          <tbody style="text-align: center;">
              <c:choose>
                 <c:when test="${not empty qcomment}">
                <c:forEach var="qcomment" items="${qcomment}">
                          <tr>
                              <td>${qcomment.a_reply_no}</td>
                              <td>관리자</td>
                              <td>${qcomment.a_details}</td>
                              <td><fmt:formatDate value="${qcomment.a_date}" pattern="yyyy-MM-dd" />
                                  <c:if test="${AUTH_USER.id eq 'admin'}">
                                      <button type="button" class="btn btn-sm btn-primary" 
                                               data-toggle="modal" data-target="#editModal" 
                                               data-a_reply_no="${qcomment.a_reply_no}" 
                                               data-QA_no="${qcomment.QA_no}" 
                                               data-a_details="${qcomment.a_details}">수정</button>
                                  </c:if>
                                  <c:if test="${AUTH_USER.id=='admin'}">
                                       <button type="button" class="btn btn-sm btn-danger" id="commentDelBtn" onclick="deleteComment(${qcomment.a_reply_no})">삭제</button>
                            </c:if>
                              </td>
                          </tr>
                      </c:forEach>
                  </c:when>
                  <c:otherwise>
                      <tr>
                          <td colspan="4">등록된 답글이 없습니다.
                          </td>
                      </tr>
                  </c:otherwise>
              </c:choose>
          </tbody>
      </table><%-- 답글목록 출력 끝 --%>

        <%-- 답글 작성 폼 --%>
      <c:if test="${AUTH_USER.id.equals('admin')}">
        <div class="mt-4">
            <form id="commentForm" action="/QCinsert.do" method="post">
                <input type="hidden" name="QA_no" id="QA_no" value="${question.QA_no}">
                <div class="form-group">
                    <label>답글 내용</label>
                    <textarea class="form-control" id="a_details" name="A_details" required="required"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">답글 작성</button>
              
            </form>
        </div>
        </c:if>
    </div>

    <!-- 수정 모달창 -->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">답글 수정</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="editForm" method="post">
                        <input type="hidden" name="a_reply_no" id="a_reply_no" />
                        <input type="hidden" name="QA_no"   id="QA_no" />
                        <div class="form-group">
                            <label for="writer">작성자</label>
                        </div>
                        <div class="form-group">
                            <label>답글 내용</label>
                            <textarea class="form-control" id="A_details" name="A_details" required></textarea>
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
        var a_reply_no = button.data('a_reply_no'); // data-A_reply_no속성의 값
        var QA_no   = button.data('QA_no'); 
        var a_details  = button.data('a_details'); 
		alert(a_reply_no);
		alert('QA_no');
		alert('a_details');
        // Modal창 내용 출력
        var modal = $(this);
        modal.find('#a_reply_no').val(a_reply_no);
        modal.find('#QA_no').val(QA_no);
        modal.find('#A_details').val(a_details);
    });

    //모달창에서 수정완료 버튼 클릭시 호출
    function updateComment() {
        // 수정된 답글 정보 가져오기
        var a_reply_no = $('#a_reply_no').val();
        var QA_no = $('#QA_no').val();
        var a_details = $('#A_details').val(); //수정된 답글내용
        //수정처리
        $.ajax({
            type: 'POST',
            url: '${cPath}/QCmodify.do',
            data: { //서버로 전송되는 data=>서버입장에서는 파라미터안에 담겨진 data
               a_reply_no:a_reply_no,
                  QA_no:QA_no,
                   a_details:a_details
            },
            dataType: 'text',
            success: function(response) {
            if(response==="success"){  //수정성공
               location.reload();
            }else{ //수정실패시
               alert("답글 수정이 실패되었습니다.");
            
            }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // 오류 발생 시 처리
                console.log('Error:', textStatus, errorThrown);
                alert('답글 수정에 오류가 발생했습니다.');
            }
        });
    }
    function deleteComment(a_reply_no) {
      var A_reply_no = $('#A_reply_no').val();
        alert(a_reply_no);
        var url = '${cPath}/QCdelete.do';
        var params = {
              A_reply_no:a_reply_no
        };
        // AJAX 요청 보내기
        $.ajax({
            type: 'POST',
            url: url,
            data: params,
            dataType: 'text',
            success: function(response) {
                    alert("답글을 삭제 했습니다.");
                    location.reload();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // 오류 발생 시 처리
                console.log('Error:', textStatus, errorThrown);
                alert('답글 삭제에 오류가 발생했습니다.');
                location.reload();

            }
        });
    }
    
    
</script>  
  
     
<%@ include file="../view/bootstrap4js.jsp" %> 
	<%@ include file="./footer.jsp" %> 


</body>
</html>