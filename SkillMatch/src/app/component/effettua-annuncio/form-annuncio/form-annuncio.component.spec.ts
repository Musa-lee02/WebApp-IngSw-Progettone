import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAnnuncioComponent } from './form-annuncio.component';

describe('FormAnnuncioComponent', () => {
  let component: FormAnnuncioComponent;
  let fixture: ComponentFixture<FormAnnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormAnnuncioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FormAnnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
