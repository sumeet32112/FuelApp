import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RaiseComplaintRoutingModule } from './raise-complaint-routing.module';
import { RaiseComplaintComponent } from './components/raise-complaint/raise-complaint.component';
import { FormsModule } from '@angular/forms';
import { RegisteredComponent } from './components/registered/registered.component';


@NgModule({
  declarations: [
    RaiseComplaintComponent,
    RegisteredComponent
  ],
  imports: [
    CommonModule,
    RaiseComplaintRoutingModule,
    FormsModule
  ]
})
export class RaiseComplaintModule { }
