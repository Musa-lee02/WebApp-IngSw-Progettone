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

  provinciaDiResidenza: string
  immagineProfilo : File;
  zonaDiCompetenza : string;
  ambitiDiCompetenza : string;
  password: string
  dataRegistrazione: Date;



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

  setImmagineProfilo(immagineProfilo : File) : void{
    this.immagineProfilo = immagineProfilo;
  }
  getImmagineProfilo() : File{
    return this.immagineProfilo;
  }

  setPassword(password : string) : void{
    this.password = password;
  }


  getPassword() {
    return this.password;
  }

  setProvincia(provinciaDiResidenza : string) : void{
    this.provinciaDiResidenza = provinciaDiResidenza;
  }

  getProvincia() : string{
    return this.provinciaDiResidenza;
  }

  setDataRegistrazione(dataRegistrazione : Date) : void{
    this.dataRegistrazione = dataRegistrazione;
  }

  getDataRegistrazione() : Date{
    return this.dataRegistrazione;
  }




}
