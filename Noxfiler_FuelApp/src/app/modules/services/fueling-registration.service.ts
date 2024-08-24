import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Fueling } from '../models/fueling.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FuelingRegistrationService {

  private baseUrl = "http://localhost:8080/fueling";

  constructor(private http:HttpClient) { }

  addFueling(fueling : Fueling): Observable<any>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Fueling>(this.baseUrl,fueling,{headers});
  }
}
