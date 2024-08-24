import { Component, OnInit } from '@angular/core';
import { Fueling } from '../../../models/fueling.model';
import { NgForm } from '@angular/forms';
import { FuelingRegistrationService } from 'src/app/modules/services/fueling-registration.service';
import { BunkService } from 'src/app/modules/services/bunk.service';
import { VehicleService } from 'src/app/modules/services/vehicle.service';
import { TokenService } from 'src/app/modules/auth/token.service';
import { UserServiceService } from 'src/app/modules/services/user-service.service';
import { Router } from '@angular/router';
import { AlertMessageService } from 'src/app/modules/services/alert-message.service';

@Component({
  selector: 'app-fueling-registration',
  templateUrl: './fueling-registration.component.html',
  styleUrls: ['./fueling-registration.component.css']
})
export class FuelingRegistrationComponent implements OnInit {

  fueling: Fueling = {} as Fueling;
  agencyList: string[] = [];
  registrationNumberList: string[] = [];
  constructor(private alertmsg: AlertMessageService,private register: FuelingRegistrationService, private bunk: BunkService, private vehicle: VehicleService,private userId : UserServiceService
    ,private router : Router) { }

  ngOnInit(): void {
    const userNumber  = this.userId.getUserId();
    this.displayAllAgency();
    this.displayAllRegistrationNumber(userNumber);
  }

  displayAllAgency() {
    this.bunk.getAllAgency().subscribe({
      next: (res: string[]) => {
        this.agencyList = res;
      },
      error: error => {
        this.alertmsg.openSnackBar(`${error}`);
      }
    })
  }

  displayAllRegistrationNumber(userNumber : string) {
    this.vehicle.getAllRegistrationNumber(userNumber).subscribe({
      next: (res: string[]) => {
        this.registrationNumberList = res;
      },
      error: error => {
        this.alertmsg.openSnackBar(`${error}`);
      }
    })
  }

  onSubmit(fuelingForm: NgForm) {

    if (this.validateForm()) {
      try {
        this.register.addFueling(this.fueling).subscribe({
          next: Response => {
            console.log(Response);
            this.alertmsg.openSnackBar("registration completed");
          },
          error: error => {
            console.log(error);
          }
        })
      } catch (error) {
        console.log(error);
      }
    }
    else {
      this.alertmsg.openSnackBar("fill up necessary fields or vehicle should be registered");
    }
  }

  validateForm(): boolean {
    return (
      !!this.fueling.agency &&
      !!this.fueling.allowedDelta &&
      !!this.fueling.dateOfFueling &&
      !!this.fueling.distanceCovered &&
      !!this.fueling.filledQuantity &&
      !!this.fueling.fuelDensity &&
      !!this.fueling.pricePerLitre &&
      !!this.fueling.timeTravelled &&
      !!this.fueling.vehicle &&
      !!this.fueling.vehicleMileage
    );
  }
}
