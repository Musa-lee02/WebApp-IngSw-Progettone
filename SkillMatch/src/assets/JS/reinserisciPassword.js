
function validatePass() {

  let password = document.forms["myForm"]["password"].value;
  let ripetiPassword = document.forms["myForm"]["ripetiPassword"].value;

  var regex =/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=]).{8,}$/
  if (regex.test(password) && regex.test(ripetiPassword)) {
    if (password === ripetiPassword) {


      window.location.href = "Profilo";
    } else {
      alert("errore password non coincidono")
    }
  }
  else {
    alert("errore password non valida")
  }

}
