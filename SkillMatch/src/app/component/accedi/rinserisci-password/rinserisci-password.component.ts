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
      password: new FormControl(null, [Validators.required, 
         Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=]).{8,}$/)
      ]),
      ripetiPassword: new FormControl(null, [Validators.required,  Validators.minLength(8),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=]).{8,}$/)])
    }, { validators: this.passwordMatchValidators });
  }

  passwordMatchValidators(control: AbstractControl) {
    const password = control.get('password')?.value;
    const ripetiPassword = control.get('ripetiPassword')?.value;

    return password === ripetiPassword ? null : { mismatch: true };
  }
}
