import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validator, Validators } from '@angular/forms';

@Component({
  selector: 'app-recupero',
  templateUrl: './recupero.component.html',
  styleUrl: './recupero.component.css'
})
export class RecuperoComponent implements OnInit {

  inputValidators : FormGroup
  ngOnInit(): void {
    
    this.inputValidators = new FormGroup({

      input:new FormControl(null, [Validators.required, Validators.email])
    })
    

  }



}
