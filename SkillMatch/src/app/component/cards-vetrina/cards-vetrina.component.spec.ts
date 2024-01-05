import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardsVetrinaComponent } from './cards-vetrina.component';

describe('CardsVetrinaComponent', () => {
  let component: CardsVetrinaComponent;
  let fixture: ComponentFixture<CardsVetrinaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CardsVetrinaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CardsVetrinaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
