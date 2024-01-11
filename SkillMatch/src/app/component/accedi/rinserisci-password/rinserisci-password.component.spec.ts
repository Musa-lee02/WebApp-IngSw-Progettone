import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RinserisciPasswordComponent } from './rinserisci-password.component';

describe('RinserisciPasswordComponent', () => {
  let component: RinserisciPasswordComponent;
  let fixture: ComponentFixture<RinserisciPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RinserisciPasswordComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RinserisciPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
