import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {User} from '../../model/User';
import {ActivatedRoute, Router} from '@angular/router';
import {LogInRequest} from '../../model/request/LogInRequest';
import {SignUpRequest} from '../../model/request/SignUpRequest';
import { environment } from '../../../environments/environment';
import {LogInResponse} from '../../model/response/LogInResponse';
import {SignUpResponse} from '../../model/response/SignUpResponse';
import {Mail} from '../../model/Mail';
import {Role} from '../../model/Role';

@Injectable()
export class AuthService {
  private baseUrl = environment.back_domain;

  constructor(private route: ActivatedRoute, private router: Router, private http: HttpClient) { }

  logIn(request: LogInRequest) {
    let endpoint: string = this.baseUrl + "login";
    return this.http.post<any>(endpoint, request,
    {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}
    ).pipe(map(
      (response: LogInResponse) => {
        if (response.status == 200) {
          let logedUser: any = new User();
          logedUser.fromResponse(response);

          logedUser = JSON.stringify(logedUser);

          sessionStorage.setItem('user', logedUser);
          sessionStorage.setItem('token', 'Bearer ' + response.data.token);
        }

        return response;
      },
      (error) => {
        return error;
      }
    ));
  }

  signUp(request: SignUpRequest) {
    let endpoint = this.baseUrl + 'signup';

    return this.http.post<any>(endpoint, request,
      {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}).pipe(map(
      (response: SignUpResponse) => {
        return response;
      },
      (error) => {
        return error;
      }
    ));
  }

  logOut() {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('token');

    this.router.navigateByUrl('signin');
  }

  isLogedIn() {
    return sessionStorage.getItem('token') !== null;
  }

  getSessionUser(): User {
    const userKey = sessionStorage.getItem('user');

    if (userKey !== null) {
      return JSON.parse(userKey);
    }

    return null;
  }

  getToken() {
    return sessionStorage.getItem('token') as string;
  }
}
