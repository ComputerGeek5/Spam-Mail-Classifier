import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {InboxRequest} from '../../model/request/InboxRequest';
import {Observable} from 'rxjs';
import {Mail} from '../../model/Mail';
import {ComposeRequest} from '../../model/request/ComposeRequest';
import {NavigationExtras, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class MailService {
  private baseUrl = environment.back_domain;

  constructor(private http: HttpClient, private router: Router) { }

  getInbox(userId: number): Observable<Mail[]> {
    let endpoint: string = this.baseUrl + `inbox/${userId}`;

    return this.http.get<any>(endpoint, {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}
    ).pipe(map(
      (response) => {
        if (response.status === 200) {
          return response.data.mails;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        throw new Error(error)
      }
    ));
  }

  getSent(userId: number): Observable<Mail[]> {
    let endpoint: string = this.baseUrl + `sent/${userId}`;

    return this.http.get<any>(endpoint, {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}
    ).pipe(map(
      (response) => {
        if (response.status === 200) {
          return response.data.mails;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        throw new Error(error)
      }
    ));
  }

  getRead(userId: number): Observable<Mail[]> {
    let endpoint: string = this.baseUrl + `read/${userId}`;

    return this.http.get<any>(endpoint, {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}
    ).pipe(map(
      (response) => {
        if (response.status === 200) {
          return response.data.mails;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        throw new Error(error)
      }
    ));
  }

  getJunk(userId: number): Observable<Mail[]> {
    let endpoint: string = this.baseUrl + `junk/${userId}`;

    return this.http.get<any>(endpoint).pipe(map(
      (response) => {
        if (response.status === 200) {
          return response.data.mails;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        throw new Error(error)
      }
    ));
  }

  compose(request: ComposeRequest) {
    let endpoint: string = this.baseUrl + `compose`;

    return this.http.post<any>(endpoint, request, {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}
    ).pipe(map(
      (response) => {
        return response;
      },
      (error) => {
        throw new Error(error)
      }
    ));
  }

  markAndRedirectWithMessage(id: number, spam: boolean, redirectUrl: string, message: string) {
    try {
      this.mark(id, spam).subscribe(
  () => {
          const navigationExtras: NavigationExtras = {state: {success: message}};
          this.router.navigate([redirectUrl], navigationExtras);
        }
      )
    } catch (error) {
      const navigationExtras: NavigationExtras = {state: {error: error.message}};
      this.router.navigate([redirectUrl], navigationExtras);
    }
  }

  mark(id: number, spam: boolean) {
    let endpoint: string = this.baseUrl + `mark/${id}`;

    if (spam !== null) {
      endpoint += `?spam=${spam}`;
    }

    return this.http.post<any>(endpoint, {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}).pipe(map(
      (response) => {
        if (response.status === 200) {
          return response;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        throw new Error(error)
      }
    ));
  }

  delete(id: number) {
    let endpoint: string = this.baseUrl + `delete/${id}`;

    return this.http.post<any>(endpoint, {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}
    ).pipe(map(
      (response) => {
        if (response.status === 200) {
          return response;
        } else {
          throw new Error(response.message);
        }
      },
      (error) => {
        throw new Error(error)
      }
    ));
  }
}
