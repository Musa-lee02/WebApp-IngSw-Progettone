// Funzione per controllare la validità dei campi e abilitare/disabilitare il pulsante di invio
function validate() {

  let x = document.forms["myForm"]["email"].value;

  var regex =/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
  if (regex.test(x)) {


    window.location.href = "Recupero/Reinserisci";
  }
  else{
    alert("email non valida")
  }


  console.log("fttft")
}
