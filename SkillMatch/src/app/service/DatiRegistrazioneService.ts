import {Injectable}  from "@angular/core";

@Injectable({
  providedIn: 'root'
})



export class DatiRegistrazioneService{
  username : string;
  email : string;
  nome : string;
  cognome : string;
  dataDiNascita : Date;

  foto : string;
  zonaDiCompetenza : string;
  ambitiDiCompetenza : string;



  setUsername(username : string) : void{
    this.username = username;
  }

  getUsername() : string{
    return this.username;
  }

  setEmail(email : string) : void{
    this.email = email;
  }

  getEmail() : string{
    return this.email;
  }

  setNome(nome : string) : void{
    this.nome = nome;
  }

  getNome() : string{
    return this.nome;
  }

  setCognome(cognome : string) : void{
    this.cognome = cognome;
  }

  getCognome() : string{
    return this.cognome;
  }

  setDataDiNascita(dataDiNascita : Date) : void{
    this.dataDiNascita = dataDiNascita;
  }

  getDataDiNascita() : Date{
    return this.dataDiNascita;
  }

  setFoto(foto : string) : void{
    this.foto = foto;
  }

  getFoto() : String{
    return this.foto;
  }

  setZonaDiCompetenza(zonaDiCompetenza : string) : void{
    this.zonaDiCompetenza = zonaDiCompetenza;
  }

  getZonaDiCompetenza() : string{
    return this.zonaDiCompetenza;
  }

  setAmbiti(ambitiDiCompetenza : string) : void{
    this.ambitiDiCompetenza = ambitiDiCompetenza;
  }

  getAmbiti() : string{
    return this.ambitiDiCompetenza;
  }




}
