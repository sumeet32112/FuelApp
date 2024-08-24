import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { AuthenticateComponent } from './authenticate/authenticate.component';

const routes: Routes = [
  {
    path:'',
    component:AuthenticateComponent
  },
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'user',
    component:UserRegistrationComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
