import { Injectable } from '@angular/core';
import {User} from '../../model/User';
import { environment } from '../../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.back_domain;

  constructor(private http: HttpClient) { }

  findAll(): Observable<User[]> {
    let endpoint = this.baseUrl + "user";
    return this.http.get<any>(endpoint,
      {headers: new HttpHeaders({ 'Content-Type': 'application/json' })})
      .pipe(map(
      (response) => {
        if (response.status === 200) {
          return response.data;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        throw new Error(error);
      }
    ));
  }
}
