import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BunkService {

  private baseUrl = "http://localhost:8080/";

  //Api End points
  private getAllAgencyEndpoint = "getAllAgency";
  private getAllBunkProviderEndpoint = "getAllBunkProvider";

  constructor(private http: HttpClient) { }

  getAllAgency():Observable<any>{
    return this.http.get<any>(`${this.baseUrl}`+`${this.getAllAgencyEndpoint}`);
  }
  
  getAllBunkProvider():Observable<any>{
    return this.http.get<any>(`${this.baseUrl}`+`${this.getAllBunkProviderEndpoint}`);
  }

}
