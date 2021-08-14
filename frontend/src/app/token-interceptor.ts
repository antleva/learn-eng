import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { AuthService } from './auth/shared/auth.service';
import { catchError, switchMap, take, filter } from 'rxjs/operators';
import { LoginResponse } from './auth/login/login-response.payload';

@Injectable({
    providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {

    constructor(public authService: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>> {
       if (req.url.indexOf('refresh') !== -1 || req.url.indexOf('logout') !== -1) {
           return next.handle(req);
       }
       const jwtToken = this.authService.getJwtToken();
       if (jwtToken) {
           return next.handle(this.addToken(req, jwtToken)).pipe(catchError(error => {
               if (error instanceof HttpErrorResponse&& error.status === 500) {
                   return this.handleAuthErrors(req, next);
               } else {
                   return throwError(error);
               }
           }));
       }
       return next.handle(req);
    }

    private handleAuthErrors(req: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>> {
       return this.authService.refreshToken().pipe(switchMap((refreshTokenResponse: LoginResponse) => {
                    return next.handle(this.addToken(req,
                        refreshTokenResponse.authenticationToken));
                })
            )
    }


    addToken(req: HttpRequest<any>, jwtToken: any) {
        return req.clone({
            headers: req.headers.set('Authorization',
                'Bearer ' + jwtToken)
        });
    }

}
