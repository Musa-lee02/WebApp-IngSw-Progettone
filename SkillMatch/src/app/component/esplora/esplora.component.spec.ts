import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EsploraComponent } from './esplora.component';

describe('EsploraComponent', () => {
  let component: EsploraComponent;
  let fixture: ComponentFixture<EsploraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EsploraComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EsploraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
