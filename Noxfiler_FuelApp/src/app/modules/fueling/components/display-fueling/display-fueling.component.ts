import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatSort, Sort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { FuelingService } from 'src/app/modules/services/fueling.service';
import { PeriodicElement } from 'src/app/modules/models/fueling.model';
import { UserServiceService } from 'src/app/modules/services/user-service.service';

/**
 * @title Table with sorting
 */

@Component({
  selector: 'app-display-fueling',
  templateUrl: './display-fueling.component.html',
  styleUrls: ['./display-fueling.component.css']
})

export class DisplayFuelingComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['date', 'registrationNumber', 'agency', 'fuelType', 'density', 'engineEconomyUsage', 'delta'];
  ELEMENT_DATA: Array<PeriodicElement> = [];
  dataSource = new MatTableDataSource(this.ELEMENT_DATA);
  constructor(private _liveAnnouncer: LiveAnnouncer, private fueling: FuelingService, private userId : UserServiceService) { }

  ngOnInit(): void {
    const userNumber  = this.userId.getUserId();
    this.displayFuelInfo(userNumber);
  }

  displayFuelInfo(userNumber : string) {
    this.fueling.getUserFuelInfo(userNumber).subscribe({
      next: (res: any) => {
        res.forEach((e: PeriodicElement) => {
          e.date = new Date(e.date)
          this.ELEMENT_DATA.push(e);
        });
      },
      error: error => {
        alert(`${error}`);
      }
    })
  }


  @ViewChild(MatSort)
  sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  /** Announce the change in sort state for assistive technology. */
  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
  formatDate(dateString: string): string {
    const dateObject = new Date(dateString);
    const day = dateObject.getDate();
    const month = dateObject.getMonth() + 1;
    const year = dateObject.getFullYear() % 100;

    const formattedDay = day < 10 ? `0${day}` : day;
    const formattedMonth = month < 10 ? `0${month}` : month;
    const formattedYear = year < 10 ? `0${year}` : year;

    return `${formattedDay}/${formattedMonth}/${formattedYear}`;
  }
 }

