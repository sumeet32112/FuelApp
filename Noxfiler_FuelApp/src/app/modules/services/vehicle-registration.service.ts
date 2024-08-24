import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Vehicle } from '../models/vehicle.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleRegistrationService {

  private baseUrl = 'http://localhost:8080/vehicles';
  constructor(private http: HttpClient) { }
  addVehicle(vehicle: Vehicle): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Vehicle>(this.baseUrl, vehicle,{headers});
}
}
