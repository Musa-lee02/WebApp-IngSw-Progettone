import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CredenzialiComponentComponent } from './credenziali-component.component';

describe('CredenzialiComponentComponent', () => {
  let component: CredenzialiComponentComponent;
  let fixture: ComponentFixture<CredenzialiComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CredenzialiComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CredenzialiComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
