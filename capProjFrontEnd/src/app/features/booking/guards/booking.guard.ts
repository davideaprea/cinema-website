import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { BookingService } from 'src/app/features/booking/services/booking.service';
import { map } from 'rxjs';
import { Role } from 'src/app/core/models/role';

export const bookingGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const bookingSvc: BookingService = inject(BookingService);
  const router = inject(Router);

  return authSvc.user.pipe(
    map(user => {
      if (user && user.role!=Role.ADMIN && bookingSvc.getSchedule() && user.isVerified) return true;
      else if (!user) router.navigate(["/auth", "login"]);
      else router.navigate(["/home", "schedules"]);
      return false;
    })
  );
}
