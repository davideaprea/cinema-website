import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingComponent } from './booking.component';
import { SeatBookingComponent } from './components/seat-booking/seat-booking.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';

const routes: Routes = [{
  path: '', component: BookingComponent, children: [
    { path: "seats", component: SeatBookingComponent },
    { path: "payment", component: PaymentComponent },
    { path: "confirmation", component: ConfirmationComponent }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookingRoutingModule { }
