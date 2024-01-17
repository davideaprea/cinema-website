import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { map } from 'rxjs';
import { Role } from 'src/app/core/models/role';

export const adminGuard: CanActivateFn = (route, state) => {
  return inject(AuthService).user.pipe(
    map(user => user?.role==Role.ADMIN)
  );
};
