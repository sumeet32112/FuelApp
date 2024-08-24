import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from '../modules/auth/auth.service';
import { AlertMessageService } from '../modules/services/alert-message.service';

@Injectable()
export class ApiInterceptor implements HttpInterceptor {

  constructor(private router : Router,private authService : AuthService,private alertmsg : AlertMessageService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token = localStorage.getItem('AUTH_TOKEN');
    if (token) {
      request = request.clone({
        setHeaders: { Authorization: `Bearer ${token}` },
      });
    }

    return next.handle(request).pipe(catchError((error:HttpErrorResponse)=> this.handleError(error)));
  }

  private handleError(response: HttpErrorResponse): Observable<any>{
    const status = response.status;
    if(status == 404){
      this.alertmsg.openSnackBar("Not found");
    }
      else {
        this.authService.doLogout();
       }
    return of(true);
  }
}
