import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faPencil } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css','../../app.component.css']
})
export class ProfiloComponent implements OnInit {

  annunci:any
  pencil=faPencil
  entita:string

  url:string;
  constructor(private service: ServizioAnnunciService, private route: ActivatedRoute){

    this.service.setAutenticato(true);

  }
  ngOnInit(): void {

    this.annunci=this.service.getAnnunci();
    console.log(this.annunci)

    if(this.route.snapshot.paramMap.get('Entita')){

      this.entita=this.route.snapshot.paramMap.get('Entita')!;
      if(this.entita==="Cliente")
        this.entita=="Cliente";
      if(this.entita==="Lavoratore")
        this.entita=="Lavoratore";
    }
  }

  onSelectFile(e:any){
    if(e.target.files){
      var reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload=(event:any)=>{
        this.url=event.target.result;
        this.service.setPicProfile(this.url)
      }

    }
  
    

  }

  getPicProfile(){

    return this.service.getPicProfile()
  }

  

}
