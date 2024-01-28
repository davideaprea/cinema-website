import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { BookingService } from 'src/app/features/booking/services/booking.service';
import { map } from 'rxjs';
import { Role } from 'src/app/core/models/role';

export const bookingGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const bookingSvc: BookingService = inject(BookingService);

  return authSvc.user.pipe(
    map(user => {
      if (user?.role!=Role.ADMIN && bookingSvc.getSchedule() && user?.isVerified) return true;
      return false;
    })
  );
}
