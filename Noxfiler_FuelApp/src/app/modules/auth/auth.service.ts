import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = "http://localhost:8080/";

  private noopEndPoint ="noop";

  constructor(private http : HttpClient, private router : Router) { }

  getNoop() {
    return this.http.get(`${this.baseUrl}`+`${this.noopEndPoint}`);
  }

  doLogout(){
    localStorage.removeItem('AUTH_TOKEN');
    localStorage.removeItem('REFRESH_TOKEN');
    localStorage.clear();
    this.router.navigate(['/login'], { replaceUrl: true });
  }


}
