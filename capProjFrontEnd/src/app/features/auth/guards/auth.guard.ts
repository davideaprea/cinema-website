import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { map } from 'rxjs';
import { AuthService } from 'src/app/features/auth/services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const router = inject(Router);

  return authSvc.isUserLogged.pipe(
    map(user => {
      if (user) return true;
      router.navigate(["/auth", "login"]);
      return false;
    })
  )
};
