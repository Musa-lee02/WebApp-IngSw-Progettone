import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SceltaUtenteComponent } from './scelta-utente.component';

describe('SceltaUtenteComponent', () => {
  let component: SceltaUtenteComponent;
  let fixture: ComponentFixture<SceltaUtenteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SceltaUtenteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SceltaUtenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
