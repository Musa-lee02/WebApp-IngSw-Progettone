import {Lavoratore} from "../model/Lavoratore";
import {Utente} from "../model/Utente";
import {Injectable} from "@angular/core";


@Injectable({
  providedIn: 'root'
})

export class LavoratoreFieldService {

  public getAmbiti(utente : Utente) : string[]{
    console.log((<Lavoratore>(utente)).ambiti)
    return (<Lavoratore>utente).ambiti.map(a => a.nome)

  }

  public getZona(utente : Utente) : string{
    return (<Lavoratore>utente).provinciaLavoro
  }


}
