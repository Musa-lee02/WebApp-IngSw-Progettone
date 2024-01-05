import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfermaEmailComponent } from './conferma-email.component';

describe('ConfermaEmailComponent', () => {
  let component: ConfermaEmailComponent;
  let fixture: ComponentFixture<ConfermaEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfermaEmailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfermaEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
