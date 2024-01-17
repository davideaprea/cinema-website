import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { User } from 'src/app/features/auth/models/user';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  private user: User | null = null;

  constructor(private authSvc: AuthService) {
    authSvc.user.subscribe(user => this.user = user);
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.user) {
      const reqClone = request.clone(
        {
          headers: request.headers.set('Authorization', 'Bearer ' + this.user.accessToken)
        }
      );
      return next.handle(reqClone);
    }
    return next.handle(request);
  }
}
