import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayFuelingComponent } from './display-fueling.component';

describe('DisplayFuelingComponent', () => {
  let component: DisplayFuelingComponent;
  let fixture: ComponentFixture<DisplayFuelingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisplayFuelingComponent]
    });
    fixture = TestBed.createComponent(DisplayFuelingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
