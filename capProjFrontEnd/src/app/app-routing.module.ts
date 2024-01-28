import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { formGuard } from './features/auth/guards/form.guard';
import { adminGuard } from './features/admin/guards/admin.guard';
import { bookingGuard } from './features/booking/guards/booking.guard';
import { authGuard } from './features/auth/guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'home/schedules', pathMatch: 'full' },
  { path: 'home', loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule) },
  { path: 'auth', loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule), canActivate:[formGuard] },
  { path: 'admin', loadChildren: () => import('./features/admin/admin.module').then(m => m.AdminModule), canActivate:[adminGuard] },
  { path: 'booking', loadChildren: () => import('./features/booking/booking.module').then(m => m.BookingModule), canActivate:[authGuard, bookingGuard] },
  { path: 'profile', loadChildren: () => import('./features/profile/profile.module').then(m => m.ProfileModule), canActivate:[authGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
