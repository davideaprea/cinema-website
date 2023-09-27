import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { IUser } from '../models/iuser';
import { map } from 'rxjs';

export const profileGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const router = inject(Router);
  const user:IUser|null=null;

  return authSvc.isUserLogged.pipe(map((user=>{
    if (user) return true;
    router.navigate(["/home"]);
    return false;
  })));
};
