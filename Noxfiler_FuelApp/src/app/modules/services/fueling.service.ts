import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FuelingService {

  private baseUrl = "http://localhost:8080/";

  //Api endpoints
  private getUserFuelInfoEndpoint = "getUserFuelInfo";

  constructor(private http:HttpClient) { }
  
  getUserFuelInfo(userId : string):Observable<any>{
      return this.http.get<any>(`${this.baseUrl}`+`${this.getUserFuelInfoEndpoint}`,{params:{userNumber:userId}});
  }
  getUserFuelInfoAgency(userId : string, agencyName : string ):Observable<any>{
    return this.http.get<any>(`${this.baseUrl}`+`${this.getUserFuelInfoEndpoint}`+`/${userId}`+`/${agencyName}`);
}
}
