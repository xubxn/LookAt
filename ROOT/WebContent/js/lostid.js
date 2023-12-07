$(document).ready(function () {
  var member_name = document.getElementById("member_name");
  var member_tel = document.getElementById("member_tel");

  var errorname = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,11}$/;
  var errortel = /^[0-9]{2,3}[0-9]{3,4}[0-9]{4}$/;

  var name = document.getElementById("name");
  var tel = document.getElementById("tel");

  member_name.addEventListener("keyup", function () {
    if (member_name.value === "") {
      member_name.style.borderColor = "red";
      name.style.display = "block";
    } else if (!errorname.test(member_name.value)) {
      member_name.style.borderColor = "red";
      name.style.display = "block";
    } else {
      name.style.display = "none";
      member_name.style.borderColor = "";
    }
  });

  member_tel.addEventListener("keyup", function () {
    if (member_tel.value === "") {
      member_tel.style.borderColor = "red";
      tel.style.display = "block";
    } else if (!errortel.test(member_tel.value)) {
      member_tel.style.borderColor = "red";
      tel.style.display = "block";
    } else {
      tel.style.display = "none";
      member_tel.style.borderColor = "";
    }
  });
});
