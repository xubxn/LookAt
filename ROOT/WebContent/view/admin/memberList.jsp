<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cPath" value="<%=request.getContextPath()%>" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LOOKAT!</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet" href="../css/memberList.css">
 <script>
 	
 $(function(){
	 
	 //조회버튼 클릭시
	 $("#searchMemberBtn").on("click",function(){
		 // 입력된 검색어 가져오기
		let userInput = document.getElementById("searchValue").value;  //<input type="text" placeholder="사용자 검색" class="form-control" id="search-value"name="searchValue" />
		
		 // 검색어가 입력되지 않았을 경우 알림 메시지 표시 후 함수 종료
		if(userInput===""){ 
			alert("검색어는 필수입력입니다.");
			//$("#searchMember").focus();
			document.getElementById("searchValue").focus();
			return;
		}
			
		 $.ajax({
			  type:"post", //요청타입."get" "post"
			  url:"${cPath}/searchMember.do",   //요청url.예)"/board/list.do"
			  data:{searchValue:userInput}, //서버로 보내는 data
			  //dateType:"text",   //서버에서 응답받는 데이터type / 예)"json","xml","html","text"등
			  success: //정상 호출 함수
			    function(response){
				  //response안에 응답내용이 문자열로 변환된 (JSONObject객체를) 받는다 
				  alert("LOOKAT!");
				  //서버에서 응답받은 json을 파싱한다ㅡ
				 const jsonObj = JSON.parse(response);
				 
				 $("#member-table-div").hide();
				 $("#member-table-div2").empty();
				 //var tableBody = $("#member-Table tbody");
				 //alert(tableBody); console.log(tableBody);
				 //tableBody.empty();
				 
					 var row = '<table class="table"><thead><tr><th></th><th class="member-id"><a>아이디</a></th><th class="member_name"><a>이름</a></th><th class="member_email"><a>이메일</a></th><th class="member_date"><a>생년월일</a></th><th class="created-at"><a>메모</a></th><th class="quit_Y"><a>탈퇴</a></th></tr></thead><tbody>';
				 if(jsonObj.member!=null && jsonObj.member.length>0){
					 
					 
					 for( let i in  jsonObj.member ){
						 row+="<tr>";
						 row+='<td> <input type="checkbox" name="members" value="'+jsonObj.member[i].member_id+'"/></td>';
						 row+='<td class="member-id">'+jsonObj.member[i].member_id+'</td>';
						 row+='<td class="member_name">'+jsonObj.member[i].member_name+'</td>';
						 row+='<td class="member_email">'+jsonObj.member[i].member_email+'</td>';
						 row+='<td class="member_date">'+jsonObj.member[i].member_date+'</td>';
						 row+='<td class="member_tel">'+jsonObj.member[i].member_tel+'</td>';
						 row+='<td class="quit_Y">'+jsonObj.member[i].quit_Y+'</td>';
						 /*row+="<td><a href='${cPath}/member/delete.do?no="+jsonObj.member[i].no+"'>삭제</a></td>";*/
						 row+="</tr>";
					//	 tableBody.append(row);
						 //$("#member-table-div2").append(row);
					 }
				 }else{
					 //var row = "<tr><td colspan='7'>회원이 존재하지 않습니다</td></tr>";
					 row+= "<tr><td colspan='7'>회원이 존재하지 않습니다</td></tr>";
					 //$("#member-table-div2").append(row);
				 }
				 row+= "</tbody></table>";	
				 $("#member-table-div2").append(row);
//					 tableBody.append(row);
					 //$("#member-table-div").append(row);
					 $("#searchValue").val("");//검색어입력부분 초기화
				 
			    },
			  error://오류발생 호출 함수. 4xx 또는 5xx
				    // jqXHR: XMLHttpRequest 객체
				    // textStatus: 에러 상태를 설명하는 문자열
				    // errorThrown: 에러의 예외 객체 (예외가 발생하지 않으면 undefined)
			  	function(jqXHR, textStatus, errorThrown) {
			      console.log("error:",textStatus,errorThrown);  	
			  	  alert("no"+textStatus); 	  
				}
			 });//ajax()끝
	  });//$("#searchMember").click()끝
 });
 
 
 
 </script>

</head>
<body>
<%@ include file="/view/header.jsp" %>                                                                                             

<header>
    <nav class="navbar navbar-light bg-light">
        <span class="navbar-brand mb-0 h1">회원관리</span>
    </nav>  
</header>

<div class="sidemenu">
    <aside class="side-bar">
        <ul>
            <li>
              
                <ul>
                    <li><a href="/memberList.do">회원관리</a></li>
                    <li><a href="/exhibitionList.do">전시회정보</a></li>
                    <li><a href="/noticeList.do">공지사항</a></li>
                    <li><a href="#">1:1문의</a></li>
                </ul>
            </li>
        </ul>
    </aside>
</div>
<div>
</div>
<div class="mainmenu">
    <div class="row">
        <div class="card card-margin search-form">
            <div class="card-body p-0">
                <form id="search-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="row no-gutters">
                                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                    <label for="search-type" hidden>검색 유형</label>
                                    <select class="form-control" id="search-type" name="searchType">
                                        <option>검색</option>
                                        <option>id</option>
                                        <option>이름</option>
                                        <option>번호</option>
                                        <option>이메일</option>
                                        <option>생년월일</option>
                                    </select>
                                </div>
                                
                                <div class="col-lg-8 col-md-6 col-sm-12 p-0">
                                    <input type="text" placeholder="사용자 검색" class="form-control" name="searchValue" id="searchValue">
                                </div>
                                
                                <div class="col-lg-1 col-md-3 col-sm-12 p-0">
                                   <button type="button" class="btn btn-base" id="searchMemberBtn">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                             viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
                                             stroke-linecap="round" stroke-linejoin="round"
                                             class="feather feather-search">
                                            <circle cx="11" cy="11" r="8"></circle>
                                            <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                                        </svg>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
   <form action="/memberDelete.do" method="post">
    <div class="row" id="member-table-div2"></div>
    
    <div class="row" id="member-table-div">
        <table class="table" id="member-table">
        
            <thead>
            <tr>
                <th></th>
                <th class="member-id">아이디</th>
                <th class="member_name">이름</th>
                <th class="member_email">이메일</th>
                <th class="member_date">생년월일</th>
                <th class="created-at">메모</th>
               	<th class="quit_Y">탈퇴</th> 
            </tr>
            </thead>
            
            <tbody>
                <%-- Replace this section with JSP code to dynamically generate the table rows based on data from the server --%>
                <%-- Example: If you have a list of members, you can use a JSP forEach loop to iterate through the list and generate rows --%>
              <c:forEach var="member" items="${memberPage.content}">
                    <tr>
                        <td>
                            <input type="checkbox" name="members" value="${member.member_id}" />
                        </td>
                        <td class="member-id">${member.member_id}</td>
                        <td class="member_name">${member.member_name}</td>
                        <td class="member_email">${member.member_email}</td>
                        <td class="member_date">${member.member_date}</td>
                        <td class="created-at">${member.member_tel}</td>
                        <td class="quit_Y">${member.quit_Y}</td> 
                    </tr>
               </c:forEach>
            </tbody>
        </table>
    </div>
    
     <div class="container"><!-- page title -->
            
     <nav aria-label="Page navigation"> <!--  justify-content-center클래스는 가운데정렬  -->
	 <ul class="pagination justify-content-center" style="margin:0 0">
	  <%--<c:if>이용하여 노출여부가 달라진다 --%>
 	  <c:if test="${memberPage.startPage>5}">
	   <li class="page-item"><a class="page-link" href="memberList.do?pageNo=${memberPage.startPage-5}">&lt;&lt;</a></li>
	  </c:if> 
	  
	  <%-- <c:forEach></c:forEach>반복문이용 --%>
	  <c:forEach var="pNo"  begin="${memberPage.startPage}" 
						    end="${memberPage.endPage}"  step="1">
	   <li class="page-item"><a class="page-link" href="memberList.do?pageNo=${pNo}">${pNo}</a></li>
	  </c:forEach>
	   
	  <%--<c:if>이용하여 노출여부가 달라진다 --%>
	  <c:if test="${memberPage.endPage< memberPage.totalPages}"> 
	   <li class="page-item"><a class="page-link" href="memberList.do?pageNo=${memberPage.startPage+5}">&gt;&gt;</a></li>
	  </c:if> 
	 </ul> 	
    </nav>
            
            
  
    <div class="row">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
    	<input type="submit" class="btn btn-default" id="member-delete" value="회원탈퇴"/>
<!--         <a class="btn btn-default" role="submit" id="member-delete">회원탈퇴</a> -->
<!--             <a class="btn btn-primary me-md-2" role="button" id="write-article">회원탈퇴</a> -->
        </div>
    </div>
</form>



</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script> 
</body>
</html>
