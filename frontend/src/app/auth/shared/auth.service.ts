import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SignupRequestPayload } from '../signup/signup-request.payload';
import { Observable, throwError } from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginRequestPayload } from '../login/login-request.payload';
import { RefreshTokenPayload } from './refresh-token.payload';
import { LoginResponse } from '../login/login-response.payload';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
    @Output() username: EventEmitter<string> = new EventEmitter();
    refreshTokenPayload: RefreshTokenPayload;

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) {}

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
     this.localStorage.clear('authenticationtoken');
     this.localStorage.clear('username');
     this.localStorage.clear('refreshtoken');
     this.localStorage.clear('expiresat');
    return this.httpClient.post('http://localhost:8080/auth/sign-up', signupRequestPayload, { responseType: 'text' });
  }

   login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
    return this.httpClient.post<LoginResponse>('http://localhost:8080/auth/login',
     loginRequestPayload).pipe(map(data => {
       this.localStorage.store('authenticationtoken', data.authenticationToken);
       this.localStorage.store('expiresat', data.expiresAt);
       this.localStorage.store('refreshtoken', data.refreshToken);
       this.localStorage.store('username', data.username);
       this.loggedIn.emit(true);
       this.username.emit(data.username);
    return true;}));
   }

   getJwtToken() {return this.localStorage.retrieve('authenticationtoken');}

   refreshToken() {
       console.log('inside refreshToken')
       this.refreshTokenPayload = {
                   username: this.getUserName(),
                   token: this.getRefreshToken()
       }
       return this.httpClient.post<LoginResponse>('http://localhost:8080/auth/refresh/token',
         this.refreshTokenPayload)
         .pipe(tap(response => {
           this.localStorage.clear('authenticationtoken');
           this.localStorage.clear('expiresat');
           this.localStorage.store('authenticationtoken', response.authenticationToken);
           this.localStorage.store('expiresat', response.expiresAt);
       }));
   }

   logout() {
       this.refreshTokenPayload = {
               token: this.getRefreshToken(),
               username: this.getUserName()
       }
       this.httpClient.post('http://localhost:8080/auth/logout', this.refreshTokenPayload,{ responseType: 'text' })
         .subscribe(data => {
           console.log(data);
         }, error => {
           throwError(error);
         })
       this.localStorage.clear('authenticationtoken');
       this.localStorage.clear('refreshtoken');
       this.localStorage.clear('expiresat');
       this.localStorage.clear('username');
   }

   getUserName() {return this.localStorage.retrieve('username');}

   getRefreshToken() {return this.localStorage.retrieve('refreshtoken');}

   isLoggedIn(): boolean {return this.getJwtToken() != null;}
}
