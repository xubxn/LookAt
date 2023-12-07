<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cPath" value ="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOOK AT</title>
<link rel="stylesheet" href="../css/css.css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="../js/join.js"></script>

<script>
function checkDuplicateID() {
    let userInputId = $("#member_id").val();
    if (userInputId === "") {
        $("#result").text("");
        return;
    }
	
    var errorid = /^([a-z][a-z\d]{3,11})$/;
    
    if (errorid.test(userInputId)) { 
    $.ajax({
        type: "post",
        url: "${cPath}/member/idCheck.do",
        data: { member_id: userInputId },
        dataType: "text",
        success: function (response) {
            console.log(response);
            if (response === "usable") {
                $("#result").text("사용 가능한 ID입니다.").css({
                											"color":"#aaaaaa",
                											"font-size":"12px",
                											"font-style":"italic",
                											"display" : "block"});
            } else if (response === "Not_usable") {
                $("#result").text("이미 사용중인 ID입니다.").css({
										                    "color": "#aaaaaa",
										                    "font-size": "12px",
										                    "font-style": "italic",
										                    "display": "block" });
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error:", textStatus, errorThrown);
        },
        complete: function (data, textStatus) {
            console.log(data, textStatus);
        },
        async: true
    });
  }
}

$(document).ready(function () {
    $("#member_id").blur(function () {
        checkDuplicateID();
    });
    $("#member_id").keyup(function () {
        $("#result").text("").css("display", "none");
    });
});
</script>


</head>
<body>

	<form class="form" name="join.jsp" action="join.do" method="post"
		 accept-charset="utf-8">

		<div class="logo">
			<img src="../view/image/LOGO.png">
		</div>

		<div class="divM">

			<div>
				<input class="text" type="text" id="member_id" name="member_id" 
					placeholder="아이디"><br/>
					<span id ="id" class="spanstyle"  >아이디는 영소문자로 시작하는 4~12글자로 입력해주세요.</span>
			</div>
					<span id="result" ></span>
			<div>
				<input class="text" type="password" id="member_pw" name="member_pw"
					placeholder="비밀번호"><br/>
				<span id ="pw" class="spanstyle">비밀번호는 8~15자리 숫자/문자를 포함해야합니다.</span>
			</div>
			<div>
				<input class="text" type="password" id="replay_pw" name="replay_pw"
					placeholder="비밀번호확인"><br/>
					<span id ="repw" class="spanstyle" >위에 입력하신 비밀번호와 동일하지 않습니다.</span>
			</div>
			<div>
				<input class="text" type="text" id="member_Name" name="member_Name"
					placeholder="이름"><br/>
					<span id ="name" class="spanstyle" >이름은 2~10글자 입력하세요.</span>
			</div>
			<div>
				<input class="text" type="text" id="member_date"
					name="member_date" placeholder="생년월일 ex)19880808"><br/>
					<span id ="date" class="spanstyle">생년월일 8자리를 입력해주세요. ex)19880808</span>
			</div>
			<div>
				<input class="text" type="tel" id="member_tel" name="member_tel"
					placeholder="전화번호 ex)01012345678"><br/>
					<span id ="tel" class="spanstyle">전화번호를 입력해주세요. ex)01012345678</span>

			</div>
			<div>
				<input class="text" type="email" id="member_email"
					name="member_email" placeholder="이메일"><br/>
					<span id ="email" class="spanstyle">이메일을 입력해주세요.</span>
			</div>
		</div>
		
		<div class="okBtn">
			<input class="ok" type="submit" style="cursor: pointer;" disabled value="가입하기">
		</div>
		

	</form>
	<%@ include file="../footer.jsp" %> 

</body>
</html>

