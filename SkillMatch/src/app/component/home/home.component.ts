import { Component, OnInit } from '@angular/core';
import { ServizioAnnunciService } from '../../service/servizio-annunci.service';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { FormControl } from '@angular/forms';
import { Observable, map, startWith } from 'rxjs';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  myControl = new FormControl();
  ambiti : any;
  province : any;
  opzioniFiltrate: Observable<string[]>;

  icon= faSearch;
  title = 'SkillMatch';
  sizeAnnunci:number=0;

  constructor(private servizioAnnunci: ServizioAnnunciService){}

  ngOnInit(): void {
    this.ambiti=this.servizioAnnunci.getAmbiti();
    this.province=this.servizioAnnunci.getProvince();
    console.log(this.ambiti)
    console.log(this.province)
    this.sizeAnnunci=this.servizioAnnunci.annunciGetSize()
    this.opzioniFiltrate = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }
  private _filter(value: string): string[] {
    const valoreFiltrato = value.toLowerCase();
    return this.ambiti.filter((option: string) => option.toLowerCase().includes(valoreFiltrato));
  }
  getRange(sizeAnnunci: number){
    return this.sizeAnnunci
  }
}

