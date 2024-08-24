import { dateInputsHaveChanged } from '@angular-material-components/datetime-picker/lib/datepicker-input-base';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Vehicle } from 'src/app/modules/models/vehicle.model';
import { AlertMessageService } from 'src/app/modules/services/alert-message.service';
import { VehicleRegistrationService } from 'src/app/modules/services/vehicle-registration.service';
import { VehicleService } from 'src/app/modules/services/vehicle.service';

@Component({
  selector: 'app-vehicle-registration',
  templateUrl: './vehicle-registration.component.html',
  styleUrls: ['./vehicle-registration.component.css']
})
export class VehicleRegistrationComponent implements OnInit {
  vehicle : Vehicle = {} as Vehicle;
  brandList:string[]=[];
  constructor(private alertmsg: AlertMessageService,private register : VehicleRegistrationService,private allBrand : VehicleService){}
  ngOnInit(): void {
        this.displayAllBrand();
  }

  displayAllBrand(){
    this.allBrand.getAllVehicleBrand().subscribe({
      next:res =>{
           this.brandList = res.data;
      },
      error : error =>{
        this.alertmsg.openSnackBar(`${error}`);
      }
    })
  }
 
  onSubmit(vehicleForm : NgForm)  {
    if( this.validateForm() ){
      try{
      this.register.addVehicle(this.vehicle).subscribe({
        next: response => {
          // Handle the successful response if needed
          if( response.success == true ){
            vehicleForm.resetForm();
            this.alertmsg.openSnackBar( response.message )
          }else{
            this.alertmsg.openSnackBar( response.message )
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
      this.alertmsg.openSnackBar( "Invalid registration Number format");
    }
  }
  validateForm(): boolean {
    //registration number format
    let regNumber : string = this.vehicle.registrationNumber;
    const regex = /^[A-Z]{2}\d{2}[A-Z]{2}\d{4}$/;
    return regex.test(regNumber);

  }

  handleRegistrationError(error: any) {
    this.alertmsg.openSnackBar( error );
    //console.error(error['error']['error']);
  }
}
