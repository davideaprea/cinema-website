import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { formGuard } from './core/guards/form.guard';
import { adminGuard } from './core/guards/admin.guard';
import { bookingGuard } from './core/guards/booking.guard';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule) },
  { path: 'auth', loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule), canActivate:[formGuard] },
  { path: 'admin', loadChildren: () => import('./features/admin/admin.module').then(m => m.AdminModule), canActivate:[adminGuard] },
  { path: 'booking', loadChildren: () => import('./features/booking/booking.module').then(m => m.BookingModule), canActivate:[bookingGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
