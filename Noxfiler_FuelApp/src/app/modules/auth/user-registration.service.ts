import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  private baseUrl = 'http://localhost:8080/register';

  constructor(private http: HttpClient) {}

  registerUser( userRegistrationData: {
    title: string;
    firstName: string;
    middleName: string;
    lastName:string;
    displayName:string;
    preferredBunkType: {
      id: number;
      name: string;
    };
    mobile: string;  
  }): Observable<any> {
    // Set the headers to specify JSON content-type
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    // Send the POST request with the JSON data and headers
    return this.http.post(this.baseUrl, JSON.stringify(userRegistrationData), { headers });
  }
}
