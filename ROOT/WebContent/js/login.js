$(document).ready(function() {
    var member_id = document.getElementById("member_id");
    var member_pw = document.getElementById("member_pw");
    var error = document.getElementById("error");

    function showError(message) {
        error.textContent = message;
        error.style.display = "block";
    }

    function hideError() {
        error.style.display = "none";
    }

    function validateForm() {
        if (member_id.value === "" || member_pw.value === "") {
            showError("아이디와 비밀번호를 모두 입력해주세요.");
        } else {
            hideError();
            // 여기서 로그인을 진행하도록 처리할 수 있습니다.
            // 아이디와 비밀번호를 서버로 전송하여 로그인 처리를 진행하거나, 가상의 checkLogin() 함수를 사용할 수 있습니다.
            if (checkLogin()) {
                alert("로그인 성공!");
                // 로그인 성공 시 원하는 동작을 수행할 수 있습니다.
            } else {
                showError("아이디 혹은 비밀번호가 틀립니다.");
            }
        }
    }

    member_id.addEventListener("keyup", function() {
        if (member_id.value === "") {
            member_id.style.borderColor = "red";
        } else {
            member_id.style.borderColor = "";
        }
        hideError();
    });

    member_pw.addEventListener("keyup", function() {
        if (member_pw.value === "") {
            member_pw.style.borderColor = "red";
        } else {
            member_pw.style.borderColor = "";
        }
        hideError();
    });


});