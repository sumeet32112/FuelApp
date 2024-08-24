import { TestBed } from '@angular/core/testing';

import { FuelingService } from './fueling.service';

describe('FuelingService', () => {
  let service: FuelingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FuelingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
