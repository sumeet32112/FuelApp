import { TestBed } from '@angular/core/testing';

import { FuelingRegistrationService } from './fueling-registration.service';

describe('FuelingRegistrationService', () => {
  let service: FuelingRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FuelingRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
