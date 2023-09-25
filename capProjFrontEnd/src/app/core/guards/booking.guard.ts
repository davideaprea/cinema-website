import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { IUser } from '../models/iuser';

export const bookingGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const router = inject(Router);
  let user:IUser|null=null;
  authSvc.isUserLogged.subscribe(data=>user=data);

  if (user && !authSvc.isUserAdmin()) return true;
  router.navigate(["/auth", "login"]);
  return false;
};
