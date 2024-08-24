import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  constructor( private http : HttpClient ) { }
  private userId : string ="";
  setUserId(userId : string){
    localStorage.setItem('USER_ID',userId);
  }
  getUserId() : string{
    return localStorage.getItem('USER_ID') || '';
  }
  getUserByUsername(): Observable<any> {
    const url = `http://localhost:8080/username/${this.getUserId()}`;
    return this.http.get(url);
  }

}
