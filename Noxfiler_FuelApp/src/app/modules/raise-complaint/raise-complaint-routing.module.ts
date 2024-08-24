import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RaiseComplaintComponent } from './components/raise-complaint/raise-complaint.component';
import { RegisteredComponent } from './components/registered/registered.component';

const routes: Routes = [ {
  path : '' , component : RaiseComplaintComponent
},
{
  path : 'registered', component : RegisteredComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RaiseComplaintRoutingModule { }
