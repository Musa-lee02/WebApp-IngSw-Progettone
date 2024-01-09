import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-rinserisci-password',
  templateUrl: './rinserisci-password.component.html',
  styleUrl: './rinserisci-password.component.css'
})
export class RinserisciPasswordComponent implements OnInit {

  
  passwordValidators: FormGroup;

  constructor() {
    
  }
  ngOnInit(): void {
    this.passwordValidators = new FormGroup({
      password: new FormControl(null, [Validators.required]),
      ripetiPassword: new FormControl(null, [Validators.required])
    }, { validators: this.passwordMatchValidators });
  }

  passwordMatchValidators(control: AbstractControl) {
    const password = control.get('password')?.value;
    const ripetiPassword = control.get('ripetiPassword')?.value;

    return password === ripetiPassword ? null : { mismatch: true };
  }
}
