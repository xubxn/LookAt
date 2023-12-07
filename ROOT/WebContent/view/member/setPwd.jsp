<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/resetpwd.js"></script>
<link rel="stylesheet" href="../css/setpwd.css"/>
<title>LOOK AT</title>
</head>
<body>

	<form action="resetpwd.do" name = "setPwd"method="post" accept-charset="utf-8" >
	<input type = "hidden" name="settel" id= "settel" value="${user.tel}">
	
		<div class="logo">
			<img src="../view/image/LOGO.png">
		</div>
		
		<div class ="form">
			<div>
				<input class="text" type="password" id="setpw" name="setpw"
					placeholder="새로운 비밀번호"><br/>
					<span id ="pw" class="spanstyle">비밀번호는 8~15자리 숫자/문자를 포함해야합니다.</span>
			</div>
			<div>
				<input class="text" type="password" id="setrepw" name="setrepw"
					placeholder="비밀번호 확인"><br/>
					<span id ="repw" class="spanstyle">위에 비밀번호와 동일하지 않습니다.</span>
			</div>
		</div>
			<div class="setBtn">
				<input class="set" type="submit" id="setbtn" style="cursor: pointer;"  value="로그인창으로 이동">
			</div>
	</form>
	
		<%@ include file="../footer.jsp" %> 
	
</body>
</html>