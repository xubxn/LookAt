<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/findid.css" />
<title>LOOK AT</title>
</head>
<body>
	 <form action="login.do" method="post" accept-charset="utf-8">
  
    <div class="logo">
      <img src="../view/image/LOGO.png">
    </div>
   
    <div class ="form">
      <p class="p">${member.member_name}<span style="color:#464646">님의 아이디는</span><br/> ${member.member_id} <span style="color:#464646">입니다.</span><span style ="color:#000069;"></span></p>
   	</div>
   	
    <div class="findid">
      <input class="idbnt" type="submit" id="idbnt"  style="cursor: pointer;"  value="로그인창으로 이동하기">
    </div>
    
  </form>
  	<%@ include file="../footer.jsp" %> 
  
</body>
</html>