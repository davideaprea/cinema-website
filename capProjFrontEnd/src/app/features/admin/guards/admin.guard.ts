import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { map } from 'rxjs';

export const adminGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const router = inject(Router);

  return authSvc.isUserLogged.pipe(
    map(user => {
      return authSvc.isUserAdmin(user);
    })
  );
};
