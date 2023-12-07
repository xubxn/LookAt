
  $(document).ready(function(){
  var member_id = document.getElementById("member_id");
  var member_pw = document.getElementById("member_pw");
  var replay_pw = document.getElementById("replay_pw");
  var member_Name = document.getElementById("member_Name");
  var member_date = document.getElementById("member_date");
  var member_tel = document.getElementById("member_tel");
  var member_email = document.getElementById("member_email");
  
  
  var id = document.getElementById("id"); 
  var pw = document.getElementById("pw"); 
  var repw = document.getElementById("repw"); 
  var name = document.getElementById("name"); 
  var date = document.getElementById("date"); 
  var tel = document.getElementById("tel"); 
  var email = document.getElementById("email"); 
  
  //유효성검사
  var errorid = /^([a-z][a-z\d]{3,11})$/;
  var errorpw = /^(?=.*\d)(?=.*[a-z]).{8,15}$/;
  var errorname = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,11}$/;
  var errordate = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
  var errortel = /^[0-9]{2,3}[0-9]{3,4}[0-9]{4}/;
  var erroremail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
  
  //id null or 유효성에 안 맞는 경우
  member_id.addEventListener("keyup", function() {
	  if (!errorid.test(member_id.value)) {
	    member_id.style.borderColor = "red";
	    id.style.display = "block";
	    member_id.focus();
	  } else {
	    id.style.display = "none";
	    member_id.style.borderColor = "";
	  }
	  return false;
	  });
  
  //pw null or 유효성에 안 맞는 경우
  member_pw.addEventListener("keyup", function() {
	  if (member_pw.value === "") {
			member_pw.style.borderColor = "red";
			pw.style.display = "block";
			member_pw.focus();
		} else if (!errorpw.test(member_pw.value)) {
			member_pw.style.borderColor = "red";
			pw.style.display = "block";
			member_pw.focus();
		}else {
			pw.style.display = "none";
			member_pw.style.borderColor = "";
		}
		return false;
	  });
  //pw null or 비번 확인이랑 다른 경우
  replay_pw.addEventListener("keyup", function() {
	    if (replay_pw.value === "") {
	        replay_pw.style.borderColor = "red";
	        repw.style.display = "block";
	        replay_pw.focus();
	    } else if (replay_pw.value !== member_pw.value) {
	        replay_pw.style.borderColor = "red";
	        repw.style.display = "block";
	        replay_pw.focus();
	    } else {
	        repw.style.display = "none";
	        replay_pw.style.borderColor = "";
	    }
	    return false;
	});
  
  //name이 null이거나 조건에 맞지 않는 경우
  member_Name.addEventListener("keyup", function() {
	    if (member_Name.value === "") {
	    	member_Name.style.borderColor = "red";
	        name.style.display = "block";
	        member_Name.focus();
	    } else if (!errorname.test(member_Name.value)) {
	    	member_Name.style.borderColor = "red";
	    	name.style.display = "block";
	        member_Name.focus();
	    } else {
	    	name.style.display = "none";
	        member_Name.style.borderColor = "";
	    }
	    return false;
	});
  
  //생년월일이 null이거나 조건에 맞지 않는 경우
  member_date.addEventListener("keyup", function() {
	    if (member_date.value === "") {
	    	member_date.style.borderColor = "red";
	        date.style.display = "block";
	        member_date.focus();
	    } else if (!errordate.test(member_date.value)) {
	    	member_date.style.borderColor = "red";
	    	date.style.display = "block";
	    	member_date.focus();
	    } else {
	    	date.style.display = "none";
	    	member_date.style.borderColor = "";
	    }
	    return false;
	});
  
  //전화번호가 null이거나 조건에 맞지 않는 경우
  member_tel.addEventListener("keyup", function() {
	    if (member_tel.value === "") {
	    	member_tel.style.borderColor = "red";
	        tel.style.display = "block";
	        member_tel.focus();
	    } else if (!errortel.test(member_tel.value)) {
	    	member_tel.style.borderColor = "red";
	    	tel.style.display = "block";
	    	member_tel.focus();
	    } else {
	    	tel.style.display = "none";
	    	member_tel.style.borderColor = "";
	    }
	    return false;
	});
  //이메일이 null이거나 조건에 맞지 않는 경우
  member_email.addEventListener("keyup", function() {
	    if (member_email.value === "") {
	    	member_email.style.borderColor = "red";
	        email.style.display = "block";
	        member_email.focus();
	    } else if (!erroremail.test(member_email.value)) {
	    	member_email.style.borderColor = "red";
	    	email.style.display = "block";
	    	member_email.focus();
	    } else {
	    	email.style.display = "none";
	    	member_email.style.borderColor = "";
	    }
	    return false;
	});
  //input이 다 채워지면 버튼 활성화
  $("form[name='join.jsp']").on('input', function() {
	  var allFilled = true;

	  $("input.text").each(function() {
	    if ($(this).val() === '') {
	      allFilled = false;
	      return false; // Exit the loop early if any input is empty
	    }
	  });

	  if (allFilled) {
	    // 모든 input이 채워져 있을 때
	    var member_id = document.getElementById("member_id");
	    var member_pw = document.getElementById("member_pw");
	    var replay_pw = document.getElementById("replay_pw");
	    var member_Name = document.getElementById("member_Name");
	    var member_date = document.getElementById("member_date");
	    var member_tel = document.getElementById("member_tel");
	    var member_email = document.getElementById("member_email");

	    if (
	      !errorid.test(member_id.value) ||
	      !errorpw.test(member_pw.value) ||
	      replay_pw.value !== member_pw.value ||
	      !errorname.test(member_Name.value) ||
	      !errordate.test(member_date.value) ||
	      !errortel.test(member_tel.value) ||
	      !erroremail.test(member_email.value)
	    ) {
	      // 조건에 맞지 않으면 버튼 비활성화
	      $(".okBtn input").prop("disabled", true);
	    } else {
	      // 조건에 모두 맞으면 버튼 활성화
	      $(".okBtn input").prop("disabled", false);
	    }
	  } else {
	    // input이 하나라도 비어있을 때
	    $(".okBtn input").prop("disabled", true);
	  }
	  
	});
		
});
  
