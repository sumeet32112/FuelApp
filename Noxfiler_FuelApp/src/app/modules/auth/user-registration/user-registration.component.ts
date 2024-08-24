import { Component, OnInit } from '@angular/core';
import { UserRegistrationService } from '../user-registration.service';
import { Router } from '@angular/router';
import { BunkService } from '../../services/bunk.service';
import { AlertMessageService } from '../../services/alert-message.service';
@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  userRegistrationData = {
    displayName: "",
    firstName: "",
    lastName: "",
    preferredBunkType: {
      id: 1,
      name: ""
    },
    middleName: "",
    mobile: "",
    title: "",
  };
  bunkProviderList : string[] = [];
  constructor(private alertmsg : AlertMessageService , private register : UserRegistrationService, private router : Router, private bunk : BunkService ){ }
  
  async submitForm() {
    if( this.validateForm() ){
      try{
      this.register.registerUser(this.userRegistrationData).subscribe({
        next: response => {
          // Handle the successful response if needed
          if( response.success == true ){
            this.resetForm();
            this.alertmsg.openSnackBar(response.message)
            this.router.navigateByUrl( '' );
          }else{
            this.alertmsg.openSnackBar(response.message)
          }
        },
        error: error => {
          console.log(error)
        },
      });
    } catch (error) {
      console.log( error );
    }
    }else{
      this.alertmsg.openSnackBar("Please fill in all required fields : ")
    }
  }
  validateForm(): boolean {
    return (
      !!this.userRegistrationData.firstName &&
      !!this.userRegistrationData.lastName &&
      !!this.userRegistrationData.displayName &&
      !!this.userRegistrationData.preferredBunkType.name &&
      !!this.userRegistrationData.mobile &&
      !!this.userRegistrationData.title
    );
  }
  handleRegistrationError(error: any) {
    this.alertmsg.openSnackBar( error );
  }
  resetForm() {
    this.userRegistrationData = {
      displayName: "",
      firstName: "",
      lastName: "",
      preferredBunkType: {
        id: 1,
        name: ""
      },
      middleName: "",
      mobile: "",
      title: "",
    };
  }
  ngOnInit(): void {
    this.displayAllBunkprovider();
  }
  displayAllBunkprovider(){
    this.bunk.getAllBunkProvider().subscribe({
      next : (res : string[]) =>{
        this.bunkProviderList = res;
      },
      error : error =>{
        this.alertmsg.openSnackBar(`${error}`)
      }
    })
  }

}

