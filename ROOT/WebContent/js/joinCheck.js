/**
 * 
 */
$(document).ready(function() {
  var member_id = document.getElementById("member_id");
  var id = document.getElementById("id");
  var errorid = /^([a-z][a-z\d]{3,11})$/;

  // 아이디 중복 체크 함수
  function checkDuplicateId() {
    var inputId = member_id.value.trim();

    // AJAX 호출
    $.ajax({
      url: "check_duplicate_id.jsp", // 중복 체크를 처리하는 JSP 파일 경로
      type: "POST",
      data: { id: inputId },
      success: function(response) {
        if (response === "duplicate") {
          // 아이디가 중복된 경우
          member_id.style.borderColor = "red";
          id.style.display = "block";
          member_id.focus();
        } else {
          // 아이디가 중복되지 않은 경우
          id.style.display = "none";
          member_id.style.borderColor = "";
        }
      },
      error: function(xhr, status, error) {
        // AJAX 호출 실패 시 처리할 내용
        console.log("AJAX Error: " + error);
      }
    });
  }

  // 아이디 입력 시 중복 체크
  member_id.addEventListener("input", function() {
    if (!errorid.test(member_id.value)) {
      // 아이디 형식이 유효하지 않은 경우
      member_id.style.borderColor = "red";
      id.style.display = "block";
      member_id.focus();
    } else {
      // 유효한 아이디 형식인 경우 중복 체크
      checkDuplicateId();
    }
  });
});
