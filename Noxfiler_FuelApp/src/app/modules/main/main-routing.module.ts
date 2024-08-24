import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { AuthGuard } from 'src/app/guards/auth.guard';

const routes: Routes = [{
  path: '',
  component: LayoutComponent,
  children: [
    {
      path: 'fueling',
      loadChildren: () =>
        import('../fueling/fueling.module').then((m) => m.FuelingModule),
    },
    {
      path: '',
      loadChildren: () =>
        import('../vehicle/vehicle.module').then((m) => m.VehicleModule),
    },
    {
      path: 'vehicle',
      loadChildren: () =>
        import('../vehicle/vehicle.module').then((m) => m.VehicleModule),
    },
    {
      path: 'raise-complain',
      loadChildren: () =>
        import('../raise-complaint/raise-complaint.module').then((m) => m.RaiseComplaintModule),
    }
  ],
  canActivate: [AuthGuard]  // this act as authentication guard for main module
  
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
