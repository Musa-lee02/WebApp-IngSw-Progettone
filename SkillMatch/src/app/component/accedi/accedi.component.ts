import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-accedi',
  templateUrl: './accedi.component.html',
  styleUrls: ['./accedi.component.css']
})
export class AccediComponent {
  @ViewChild('container') container: ElementRef | undefined;
  @ViewChild('register') registerBtn: ElementRef | undefined;
  @ViewChild('login') loginBtn: ElementRef | undefined;

  constructor() {}

  removeActive() {
    
        if (this.container) {
          console.log(this.container);
          this.container.nativeElement.classList.remove('active');
        }
      
    }
  
  addActive() {
    if (this.container) {
      console.log(this.container);
      this.container.nativeElement.classList.add('active');
  
  }
}
}