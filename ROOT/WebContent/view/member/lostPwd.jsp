<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="../js/lostpwd.js"></script>
<link rel="stylesheet" href="../css/lostpwd.css" />
<title>LOOK AT</title>
</head>
<body>

	<form action="setpwd.do" method="post" accept-charset="utf-8">

		<div class="logo">
			<img src="../view/image/LOGO.png">
		</div>

		<div class="form">
			<div>
				<input class="text" type="text" id="lostname" name="lostname"
					placeholder="이름"><span id="name" class="spanstyle">가입시
					입력한 이름을 적어주세요</span>
			</div>
			<div>
				<input class="text" type="text" id="losttel" name="losttel"
					placeholder="핸드폰번호"><br /> <span id="tel" class="spanstyle">가입시
					입력한 전화번호를 적어주세요</span>
			</div>
			
		</div>
		<div class="setBtn">
			<input class="set" id="set" type="submit" style = "cursor: pointer;"  value="비밀번호 찾기">
		</div>

	</form>

	<%@ include file="../footer.jsp" %> 

</body>
</html>