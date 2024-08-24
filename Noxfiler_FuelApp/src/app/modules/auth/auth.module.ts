import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { FormsModule} from '@angular/forms';
import { NgOtpInputModule } from 'ng-otp-input';
import { AuthenticateComponent } from './authenticate/authenticate.component';
import { HeaderComponent } from './header/header.component';


@NgModule({
  declarations: [
    LoginComponent,
    UserRegistrationComponent,
    AuthenticateComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
    NgOtpInputModule
  ]
})
export class AuthModule { }
