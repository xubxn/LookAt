<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOOK AT</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700;900&family=Raleway:wght@600;700;800;900&family=Roboto:wght@300;400;500;700;900&display=swap" rel="stylesheet">
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="../css/logincss.css" />
<script src="../js/login.js"></script>
</head>
<body>
	<form class="form" accept-charset="utf-8" name="login.jsp" action="login.do" method="post" >
		
		
		<div class="logo">
			<img src="../view/image/LOGO.png">
		</div>
		
	<div class="divM">
		<div>
			<input class="text" type="text" id="member_id" name="member_id"
				placeholder="아이디">
		</div>
		<div>
			<input class="text" type="password" id="member_pw" name="member_pw"
				placeholder="비밀번호">
		</div>
		<span id ="error" class="spanstyle"></span>
		
		
		</div>
	</div>
		
		<div class="loginBtn">
			<input class="login" type="submit" style="cursor: pointer;"  value="로그인">
		</div>
		
	<div class="naverBtn">
		<INPUT CLASS="naver" id="naverLogin" TYPE="button" 
		 STYLE="CURSOR: POINTER;" VALUE="NAVER 아이디로 로그인"/>
	</div>
	<%--네이버에서 제공하는 무조건 써야하는 이미지 --%>
	  <div id="naver_id_login"  style="display: none;"></div>
	<script type="text/javascript">
    // Naver login
  	var naver_id_login = new naver_id_login("RrV9r97FmFEGFKslql0F", "http://localhost/index.jsp");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("green", 3,40);
  	naver_id_login.setDomain("http://localhost");
  	naver_id_login.setState(state);
  	naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
  	
  	function naverSignInCallback() {
  		alert(naver_id_login.getProfileData('email'));
  		alert(naver_id_login.getProfileData('nickname'));
  		alert(naver_id_login.getProfileData('age'));
  	}
  	// 네이버 사용자 프로필 조회
  	if(naver_id_login.is_callback == true){
  	naver_id_login.get_naver_userprofile("naverSignInCallback()");
  	}
  	//내가 만든 네이버 로그인을 눌렀을때 네이버에서 제공해주는 이미지로 연결
  	$(document).on("click", "#naverLogin", function(){ 
		var btnNaverLogin = document.getElementById("naver_id_login").firstChild;
		btnNaverLogin.click();
	});
  </script>

		<div class="kakaoBtn" >
			<input class="kakao" id="kakao-login-btn" type="button"  style="cursor: pointer;" value="KAKAO 아이디로 로그인">
		</div>
		
		<!--카카오 밑에  -->
	<div class="oneline">
		<div class="id">
			<input class="iddo" type="button" onclick="goIdfind()" style="cursor: pointer;" value="아이디찾기">
		</div>
		<div class="pw">
			<input class="pwdo" type="button" onclick="goPwdfind()" style="cursor: pointer;" value="비밀번호찾기">
		</div>
		
		
		<div class="join">
			<input class="joindo" type="button" onclick="goJoinPage()" style="cursor: pointer;" value="회원가입">
		</div>
		
		
	</div>
	<script type="text/javascript">
	function goJoinPage(){
		location.href = "./join.do";
	}
	function goPwdfind(){
		location.href = "./lostpwd.do";
	}
	
	function goIdfind(){
		location.href = "./lostid.do";
	}
	function info(){
		location.href = "./Qlist.do";
	}
	
	
	
	//카카오톡 로그인
	Kakao.init('a437fa831f25566e48878020f13cd5c0');
	$("#kakao-login-btn").on("click", function(){
	    //1. 로그인 시도
	    Kakao.Auth.login({
	        success: function(authObj) {
	         
	          //2. 로그인 성공시, API 호출
	          Kakao.API.request({
	            url: '/v2/user/me',
	            success: function(res) {
	              console.log(res);
	              var id = res.id;
	              scop:'profile_nickname, profile_image, account_email, gender, age_range, birthday';
	              location.href="index.jsp";
	        }
	          })
	          console.log(authObj);
	          var token = authObj.access_token;
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	      });
	}); //카카오
</script>


	
	<!--이용 약관부분  -->
	<div class="endline">
		<div class="role">
			<input class="roeldo" id="agreeBnt" type="button"  
			style="cursor: pointer;" onclick="agree();" value="이용약관">
		</div>
		<script type="text/javascript">
		function agree(){
			window.open("view/member/agree.jsp","LOOK AT", "width=600,height=800");
		}
		</script>
		<span> | </span>
		
		<div class="info">
			<input class="infodo"  type="button" onclick="info()" style="cursor: pointer;" value="고객센터">
		</div>
	</div>

	</form>

		<%@ include file="../footer.jsp" %>
</body>
</html>