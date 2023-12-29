import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-esplora',
  templateUrl: './esplora.component.html',
  styleUrl: './esplora.component.css'
})
export class EsploraComponent {

    constructor(private route: ActivatedRoute){

    }
}
