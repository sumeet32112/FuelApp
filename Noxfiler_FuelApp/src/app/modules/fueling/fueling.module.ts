import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FuelingRoutingModule } from './fueling-routing.module';
import { FuelingRegistrationComponent } from './components/fueling-registration/fueling-registration.component';
import { LayoutComponent } from './components/layout/layout.component';
import { MatTabsModule } from '@angular/material/tabs';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DisplayFuelingComponent } from './components/display-fueling/display-fueling.component';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';


@NgModule({
  declarations: [
    FuelingRegistrationComponent,
    LayoutComponent,
    DisplayFuelingComponent
  ],
  imports: [
    CommonModule,
    FuelingRoutingModule,
    MatTabsModule,
    FormsModule,
    MatDatepickerModule,
    MatTableModule,
    MatSortModule,
    MatSnackBarModule,
    MatFormFieldModule,
  ]
})
export class FuelingModule { }
