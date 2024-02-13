import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { map } from 'rxjs';
import { Role } from 'src/app/core/models/role';
import { SharingBookingDataService } from '../services/sharing-booking-data.service';

export const bookingGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const bookingSvc: SharingBookingDataService = inject(SharingBookingDataService);

  return authSvc.user.pipe(
    map(user => !!(user?.role!=Role.ADMIN && bookingSvc.schedule && user?.isVerified))
  );
}
