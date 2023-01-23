import { Injectable } from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor (private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    route = this.activatedRoute.snapshot;

    const currentUser = this.authService.getSessionUser();
    if (currentUser) {
      const routeRoles: string = JSON.stringify(route.data.role);
      const userRoles: string = JSON.stringify(currentUser.role);

      if (route.data.roles && routeRoles !== userRoles) {
        this.router.navigate(['/']);
        return false;
      }

      return true;
    }

    this.router.navigate(['login'], { queryParams: { returnUrl: state.url } });
    return false;
  }

}
