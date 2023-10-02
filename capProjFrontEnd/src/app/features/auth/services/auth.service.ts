import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IRegUser } from '../models/ireg-user';
import { environment } from 'src/environments/environment.development';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { ILogUser } from '../models/ilog-user';
import { IUser } from 'src/app/core/models/iuser';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Role } from 'src/app/core/models/role';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedUser = new BehaviorSubject<null | IUser>(null);
  isUserLogged = this.loggedUser.asObservable();
  private storageUser: IUser;

  constructor(private http: HttpClient, private router: Router, private jwtHelper: JwtHelperService) {
    if (localStorage.getItem("user")) this.storageUser = JSON.parse(localStorage.getItem("user")!);
    else this.storageUser = JSON.parse(sessionStorage.getItem("user")!);

    if (this.storageUser) this.loggedUser.next(this.storageUser);
  }

  getUserRole():Role{
    let decodedToken = this.jwtHelper.decodeToken(this.storageUser.accessToken);
    return decodedToken.role[0].roleName;
  }

  isUserAdmin(user:IUser|null): boolean {
    if (user) {
      let decodedToken = this.jwtHelper.decodeToken(user.accessToken);
      let role=decodedToken.role[0].roleName;
      return role == Role.ADMIN || role==Role.MODERATOR;
    }
    return false;
  }

  register(user: IRegUser) {
    return this.http.post(environment.register, user);
  }

  login(user: ILogUser, remember: boolean) {
    this.http.post<IUser>(environment.login, user).subscribe(u => {
      this.loggedUser.next(u);
      this.storageUser=u;

      if (remember) localStorage.setItem("user", JSON.stringify(u));
      else sessionStorage.setItem("user", JSON.stringify(u));

      this.router.navigate(["/home", "schedules"]);
    });
  }

  logout() {
    this.loggedUser.next(null);
    if (localStorage.getItem("user")) localStorage.removeItem("user");
    else sessionStorage.removeItem("user");
    this.router.navigate(["/home", "schedules"]);
  }
}
