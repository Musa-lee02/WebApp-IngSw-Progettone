import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServizioAnnunciService {

  url='https://material.angular.io/assets/img/examples/shiba2.jpg'
  annunci=[{
    img:'https://material.angular.io/assets/img/examples/shiba2.jpg', 
  descrizione:"   The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan."+
  "A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally"+
  "bred for hunting."},
  {
    img:'https://www.purina.co.uk/sites/default/files/2021-02/BREED%20Hero_0084_miniature_pinscher.jpg',
    descrizione:"cecedccccccccccccccccccccccccccccccc"+
    "cccccccccccccccccccccccccccccccc"
  }
]
  constructor() { }

  annunciGetSize(){
    return this.annunci.length
  }
  annunciGet(){

    return this.annunci;
  }

}
