import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EffettuaAnnuncioComponent } from './effettua-annuncio.component';

describe('EffettuaAnnuncioComponent', () => {
  let component: EffettuaAnnuncioComponent;
  let fixture: ComponentFixture<EffettuaAnnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EffettuaAnnuncioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EffettuaAnnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
