import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehicleRoutingModule } from './vehicle-routing.module';
import { VehicleRegistrationComponent } from './components/vehicle-registration/vehicle-registration.component';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { LayoutComponent } from './components/layout/layout.component';
import { MatTabsModule } from '@angular/material/tabs';
import { DisplayVehiclesComponent } from './components/display-vehicles/display-vehicles.component';
import { MatTable, MatTableModule } from '@angular/material/table';
import { MatSort, MatSortModule } from '@angular/material/sort';

@NgModule({
  declarations: [
    VehicleRegistrationComponent,
    LayoutComponent,
    DisplayVehiclesComponent
  ],
  imports: [
    CommonModule,
    VehicleRoutingModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatTabsModule,
    MatTableModule,
    MatSortModule  
  ]
})
export class VehicleModule { }
