import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayVehiclesComponent } from './display-vehicles.component';

describe('DisplayVehiclesComponent', () => {
  let component: DisplayVehiclesComponent;
  let fixture: ComponentFixture<DisplayVehiclesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisplayVehiclesComponent]
    });
    fixture = TestBed.createComponent(DisplayVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
