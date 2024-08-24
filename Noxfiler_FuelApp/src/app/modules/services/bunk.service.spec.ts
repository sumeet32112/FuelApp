import { TestBed } from '@angular/core/testing';

import { BunkService } from './bunk.service';

describe('BunkService', () => {
  let service: BunkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BunkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
