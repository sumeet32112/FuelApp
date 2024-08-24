import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { DisplayFuelingComponent } from './components/display-fueling/display-fueling.component';
import { FuelingRegistrationComponent } from './components/fueling-registration/fueling-registration.component';

const routes: Routes = [
  {
    path:'',
    component:LayoutComponent,
  },
  {
    path:'displayFueling',
    component:DisplayFuelingComponent
  },
  {
    path:'fuelingRegistration',
    component:FuelingRegistrationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FuelingRoutingModule { }
