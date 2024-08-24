import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FuelingRegistrationComponent } from './fueling-registration.component';

describe('FuelingRegistrationComponent', () => {
  let component: FuelingRegistrationComponent;
  let fixture: ComponentFixture<FuelingRegistrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FuelingRegistrationComponent]
    });
    fixture = TestBed.createComponent(FuelingRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
