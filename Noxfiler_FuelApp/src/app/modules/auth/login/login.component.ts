import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { TokenService } from '../token.service';
import { UserServiceService } from '../../services/user-service.service';
import { AlertMessageService } from '../../services/alert-message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  number:string="";
  status:boolean=true;
  constructor(private alertmsg : AlertMessageService ,private http:HttpClient, private route : Router, private userService : UserServiceService, private tokenService : TokenService){}

  login(){
   let params ={
      "number" : this.number
    }
    this.http.post<any>("http://localhost:8080/login",params).subscribe({
      next:data=>{
         this.status = false;
         console.log(data);
      },
      error: error=>{
        this.alertmsg.openSnackBar("enter valid credentials")
        console.log(error);
      }
    })   
    
  }
  
  onOtpChange(value:any){

     if(value.length == 5){
       
      let params = {
        "otp" : value,
        "number" : this.number
      }

      this.http.post<any>("http://localhost:8080/otpVerification",params).subscribe({
        next:data=>{
          if(data["token"]){
            this.tokenService.storeToken(data["token"]);
            const userId = this.number;
            this.userService.setUserId(userId);
            this.route.navigateByUrl('vehicle');
          }
          else{
            this.alertmsg.openSnackBar("error while login")
            this.route.navigateByUrl('login');
          }
        },
        error: error=>{
          this.alertmsg.openSnackBar("enter valid otp")
          console.log(error);
        }
      })   
      
     }
      
  }

}
