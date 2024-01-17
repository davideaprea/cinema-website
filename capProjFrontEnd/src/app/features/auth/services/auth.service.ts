import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IRegUser } from '../models/ireg-user';
import { environment } from 'src/environments/environment.development';
import { Router } from '@angular/router';
import { BehaviorSubject, map, tap } from 'rxjs';
import { ILogUser } from '../models/ilog-user';
import { IUser } from 'src/app/core/models/iuser';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UtilityService } from 'src/app/core/services/utility.service';
import { DecodedToken } from '../models/decoded-token';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedUser = new BehaviorSubject<null | IUser>(null);
  user = this.loggedUser.asObservable().pipe(
    map(rawUser => {
      if(!rawUser) return null;

      const decodedToken:DecodedToken=this.jwtHelper.decodeToken(rawUser.accessToken)!;
      return <User> {
        username: rawUser.username,
        role: decodedToken.role[0].roleName,
        isVerified: decodedToken.verified,
        accessToken: rawUser.accessToken
      }
    })
  );
  private storageUser: IUser | undefined;

  constructor(private http: HttpClient, private router: Router, private jwtHelper: JwtHelperService, private utilityService:UtilityService) {

    this.storageUser=utilityService.getLocalStorageItem("user") || utilityService.getSessionStorageItem("user");

    if (this.storageUser) this.loggedUser.next(this.storageUser);
  }

  register(user: IRegUser) {
    return this.http.post(environment.register, user);
  }

  login(user: ILogUser, remember: boolean) {
    return this.http.post<IUser>(environment.login, user).pipe(
      tap(u => {
        this.loggedUser.next(u);
        this.storageUser = u;

        if (remember) localStorage.setItem("user", JSON.stringify(u));
        else sessionStorage.setItem("user", JSON.stringify(u));

        this.router.navigate(["/home", "schedules"]);
      })
    )
  }

  verifyEmail(token: string) {
    return this.http.get(environment.verification + "/" + token);
  }

  logout() {
    this.loggedUser.next(null);
    if (localStorage.getItem("user")) localStorage.removeItem("user");
    else sessionStorage.removeItem("user");
    this.router.navigate(["/home", "schedules"]);
  }
}
