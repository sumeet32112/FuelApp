import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class AlertMessageService {

  constructor( private snackBar : MatSnackBar ) { 
  }
  openSnackBar( msg: string ) {
    this.snackBar.open( msg , 'Dismiss', {
      duration: 3000,
    });
  }
}
