<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="../js/lostid.js"></script>
<link rel="stylesheet" href="../css/lostid.css" />
<title>LOOK AT</title>
</head>
<body>
	
	
	<form action="lostid.do" name = "lostID" method="post" accept-charset="utf-8" >
	
		<div class="logo">
			<img src="../view/image/LOGO.png">
		</div>
		
		<div class ="form">
      <div>
        <input class="text" type="text" id="member_name" name="member_name" placeholder="이름"><br/>
        <span id ="name" class="spanstyle">가입시 입력한 이름을 적어주세요</span>
      </div>
      <div>
        <input class="text" type="text" id="member_tel" name="member_tel" placeholder="핸드폰번호"><br/>
         <span id ="tel" class="spanstyle">가입시 입력한 전화번호를 적어주세요</span>
      </div>
   	</div>
    <div class="FindId">
      <input class="idbtn" type="submit" id="idbtn"  style="cursor: pointer;"  value="아이디 확인하기">
    </div>
    
  </form>

</body>
</html>