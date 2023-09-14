import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';

export const bookingGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const router = inject(Router);

  if (!authSvc.isUserAdmin()) return true;
  router.navigate(["/home"]);
  return false;
};
