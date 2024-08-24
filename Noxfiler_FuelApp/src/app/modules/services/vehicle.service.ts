import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from 'rxjs';
import { UserServiceService } from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private baseUrl = "http://localhost:8080/";

  //Api End points
  private getAllVehicleBrandEndpoint="getAllVehicleBrand";
  private getAllRegistrationNumberEndpoint = "getAllRegistrationNumber";
  private getVehicleEndPoint = "getVehiclesOfUser";

  
  constructor(private http: HttpClient, private userId : UserServiceService ) { }
 
  getAllVehicleBrand():Observable<any>{
    return this.http.get<any>(`${this.baseUrl}`+`${this.getAllVehicleBrandEndpoint}`);
  }

  getAllRegistrationNumber(userId :string):Observable<any>{
    return this.http.get<any>(`${this.baseUrl}`+`${this.getAllRegistrationNumberEndpoint}`,{params:{userNumber:userId}});
  }
  getVehiclesOfUser():Observable<any>{
    console.log( `${this.baseUrl}`+`${this.getVehicleEndPoint}` + `/${this.userId.getUserId()}` );
      return this.http.get<any>(`${this.baseUrl}`+`${this.getVehicleEndPoint}` + `/${this.userId.getUserId()}`);
  }

}
