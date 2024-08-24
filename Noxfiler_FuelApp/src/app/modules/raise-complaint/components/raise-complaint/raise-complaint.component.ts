import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PeriodicElement } from 'src/app/modules/models/fueling.model';
import { AlertMessageService } from 'src/app/modules/services/alert-message.service';
import { BunkService } from 'src/app/modules/services/bunk.service';
import { EscalationService } from 'src/app/modules/services/escalation.service';
import { FuelingService } from 'src/app/modules/services/fueling.service';
import { UserServiceService } from 'src/app/modules/services/user-service.service';
// import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-raise-complaint',
  templateUrl: './raise-complaint.component.html',
  styleUrls: ['./raise-complaint.component.css'],
})
export class RaiseComplaintComponent implements OnInit {
  ELEMENT_DATA: Array<PeriodicElement> = [];

  agencyList: string[] = [];
  agencyName = ""
  escalationLevel = ""
  isProcessing = false;

  selectedAttachments: File[] = [] ;

  constructor(private alertmsg:AlertMessageService, private escalation : EscalationService, private router : Router, private bunk: BunkService, private fueling: FuelingService, private userId : UserServiceService) {
  }
  ngOnInit(): void {
    this.bunk.getAllAgency().subscribe({
      next: (res: string[]) => {
        this.agencyList = res;
      },
      error: error => {
        this.alertmsg.openSnackBar(`${error}`);
      }
    })
  }

  async onAgencyNameChange(){
    this.ELEMENT_DATA = [];
    this.fueling.getUserFuelInfoAgency(this.userId.getUserId(),this.agencyName).subscribe({
      next: (res: any) => {
        res.forEach((e: PeriodicElement) => {
          e.date = new Date(e.date)
          this.ELEMENT_DATA.push(e);
        });
      },
      error: error => {
        this.alertmsg.openSnackBar(`${error}`);
      }
    })
  }
  formatDate(date: Date): string {
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear() % 100;
  
    const formattedDay = day < 10 ? `0${day}` : day;
    const formattedMonth = month < 10 ? `0${month}` : month;
    const formattedYear = year < 10 ? `0${year}` : year;
  
    return `${formattedDay}/${formattedMonth}/${formattedYear}`;
  }
  removeEntry(index : number){
    this.ELEMENT_DATA.splice(index, 1);
  }
  isButtonDisabled(): boolean {
    return this.escalationLevel === "" || this.ELEMENT_DATA.length === 0 ;
  }
  submitComplaintForm() {
    this.isProcessing = true
      try{
        this.escalation.emailComplaint(JSON.stringify(this.ELEMENT_DATA), this.selectedAttachments,this.escalationLevel).subscribe({
          next: response => {
            if( response.success == true ){
              this.alertmsg.openSnackBar( response.message )
              this.router.navigateByUrl('/raise-complain/registered')
            }else{
              this.alertmsg.openSnackBar( response.message )
              this.isProcessing = false;
            }
          },
          error: error => {
            this.alertmsg.openSnackBar(error)
          },
        });
        }catch (error) {
          console.log(error );
          this.isProcessing = false;
        }
  }
  onFileSelected(event: any) {
    const fileInput: HTMLInputElement = event.target;
    
    if (fileInput.files && fileInput.files.length > 0) {
      if (this.selectedAttachments.length >= 2) {
        this.selectedAttachments.shift(); 
      }
      this.selectedAttachments.push(fileInput.files[0]); 
    }
  }
  removeAttachment(index: number) {
    this.selectedAttachments.splice(index, 1);
  }
  }
