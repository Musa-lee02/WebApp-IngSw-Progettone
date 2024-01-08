import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiepilogoDatiComponent } from './riepilogo-dati.component';

describe('RiepilogoDatiComponent', () => {
  let component: RiepilogoDatiComponent;
  let fixture: ComponentFixture<RiepilogoDatiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RiepilogoDatiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RiepilogoDatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
