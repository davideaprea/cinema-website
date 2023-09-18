import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { BookingComponent } from './booking.component';
import { StepsModule } from 'primeng/steps';
import { PaymentComponent } from './components/payment/payment.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { SeatBookingComponent } from './components/seat-booking/seat-booking.component';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [
    BookingComponent,
    PaymentComponent,
    ConfirmationComponent,
    SeatBookingComponent
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    StepsModule,
    ButtonModule
  ]
})
export class BookingModule { }
