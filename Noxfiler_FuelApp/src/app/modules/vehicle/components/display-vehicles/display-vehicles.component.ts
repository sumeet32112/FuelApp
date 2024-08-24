import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatSort, Sort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { ColumnElements } from 'src/app/modules/models/vehicle.model';
import { UserServiceService } from 'src/app/modules/services/user-service.service';
import { VehicleService } from 'src/app/modules/services/vehicle.service';

/**
 * @title Table with sorting
 */

@Component({
  selector: 'app-display-vehicles',
  templateUrl: './display-vehicles.component.html',
  styleUrls: ['./display-vehicles.component.css']
})

export class DisplayVehiclesComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['registrationNumber', 'vehicleOwner', 'vehicleBrand', 'fuelType', 'monthManufactured', 'yearManufactured', 'engineCapacity','colour'];
  ELEMENT_DATA: Array<ColumnElements> = [];
  dataSource = new MatTableDataSource(this.ELEMENT_DATA);
  constructor(private _liveAnnouncer: LiveAnnouncer, private vehicles: VehicleService, private userId : UserServiceService) { }

  ngOnInit(): void {
    this.displayVehicles();
  }
  displayVehicles() {
    this.vehicles.getVehiclesOfUser().subscribe({
      next: (res: Array<ColumnElements>) => {
        res.forEach((e: ColumnElements) => {
          this.ELEMENT_DATA.push(e);
        });
      },
      error: error => {
        alert(`${error}`);
      }
    })
  }

  addVehicle(e: ColumnElements) {
    this.ELEMENT_DATA.push(e);
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

}
