import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecuperoComponent } from './recupero.component';

describe('RecuperoComponent', () => {
  let component: RecuperoComponent;
  let fixture: ComponentFixture<RecuperoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RecuperoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RecuperoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
