import { Injectable, inject } from "@angular/core";
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateFn, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TokenService } from "../modules/auth/token.service";


@Injectable({
  providedIn: 'root'
})
class PermissionsService {

  constructor(private router: Router,private tokenService : TokenService) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const token = localStorage.getItem('AUTH_TOKEN');
    if (!token) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree => {
  return inject(PermissionsService).canActivate(next, state);
}
