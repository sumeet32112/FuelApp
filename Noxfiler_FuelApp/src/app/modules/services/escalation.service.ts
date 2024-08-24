import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EscalationService {
  private baseUrl = 'http://localhost:8080/complain';
  constructor( private http: HttpClient ) { }

  emailComplaint( fuelingdata : string , attachments : File[] , escalationLevel:string ): Observable<any> {

    const formData = new FormData();
    formData.append('fuels', new Blob([fuelingdata], { type: 'application/json' }) );
    for (const attachment of attachments) {
      formData.append('attachments', attachment, attachment.name );
    }
    return this.http.post(this.baseUrl +`/${escalationLevel}`, formData );
}
}
