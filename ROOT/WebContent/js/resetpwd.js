// JavaScript
$(document).ready(function() {
  var setpw = document.getElementById("setpw");
  var setrepw = document.getElementById("setrepw");
  var pw = document.getElementById("pw");
  var repw = document.getElementById("repw");
  var errorpw = /^(?=.*\d)(?=.*[a-z]).{8,15}$/;

  setpw.addEventListener("keyup", function() {
    validatePassword();
  });

  setrepw.addEventListener("keyup", function() {
    validatePasswordConfirmation();
  });

  $("#setbtn").on("click", function() {
    if (validatePassword() && validatePasswordConfirmation()) {
    } else {
    }
  });

  function validatePassword() {
    if (setpw.value === "") {
      setpw.style.borderColor = "red";
      pw.classList.add("error");
      return false;
    } else if (!errorpw.test(setpw.value)) {
      setpw.style.borderColor = "red";
      pw.classList.add("error");
      return false;
    } else {
      pw.classList.remove("error");
      setpw.style.borderColor = "";
      return true;
    }
  }

  function validatePasswordConfirmation() {
    if (setrepw.value === "") {
      setrepw.style.borderColor = "red";
      repw.classList.add("error");
      return false;
    } else if (setrepw.value !== setpw.value) {
      setrepw.style.borderColor = "red";
      repw.classList.add("error");
      return false;
    } else {
      repw.classList.remove("error");
      setrepw.style.borderColor = "";
      return true;
    }
  }
});
