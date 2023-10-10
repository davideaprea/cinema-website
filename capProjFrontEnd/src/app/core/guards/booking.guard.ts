import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { BookingService } from 'src/app/features/booking/services/booking.service';
import { map } from 'rxjs';

export const bookingGuard: CanActivateFn = (route, state) => {
  const authSvc: AuthService = inject(AuthService);
  const bookingSvc: BookingService = inject(BookingService);
  const router = inject(Router);

  return authSvc.isUserLogged.pipe(map(user=>{
    if (user && !authSvc.isUserAdmin(user) && bookingSvc.getSchedule() && authSvc.isUserVerified()) return true;
    else if(!user) router.navigate(["/auth", "login"]);
    else router.navigate(["/home", "schedules"]);
    return false;
  }));
};
