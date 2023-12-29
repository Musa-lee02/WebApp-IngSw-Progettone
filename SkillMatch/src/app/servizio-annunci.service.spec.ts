import { TestBed } from '@angular/core/testing';

import { ServizioAnnunciService } from './servizio-annunci.service';

describe('ServizioAnnunciService', () => {
  let service: ServizioAnnunciService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServizioAnnunciService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
