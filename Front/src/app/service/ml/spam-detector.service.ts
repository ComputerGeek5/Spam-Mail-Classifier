import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SpamDetectorService {
  private baseUrl = environment.ml_domain;

  constructor(private http: HttpClient) { }

  classify(message: string) {
    let endpoint = this.baseUrl + `classify`;

    let queryParams = new HttpParams();
    queryParams = queryParams.append("message", message);

    return this.http.get<any>(endpoint,{params:queryParams}).pipe(map(
      (response: any) => {
        console.log(response);
        if (response.status === 200) {
          return response.spam;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        console.log(error);
        throw new Error(error.message());
      }
    ));
  }
}
