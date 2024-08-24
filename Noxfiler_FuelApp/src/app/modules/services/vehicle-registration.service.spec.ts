import { TestBed } from '@angular/core/testing';

import { VehicleRegistrationService } from './vehicle-registration.service';

describe('VehicleRegistrationService', () => {
  let service: VehicleRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehicleRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
