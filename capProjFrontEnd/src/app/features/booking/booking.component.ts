import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss']
})
export class BookingComponent {
  items: MenuItem[];

  constructor() {
    this.items = [
      {
        label: 'Choose your seats',
        routerLink: 'seats'
      },
      {
        label: 'Payment',
        routerLink: 'payment'
      },
      {
        label: 'Confirmation',
        routerLink: 'confirmation'
      }
    ];
  }
}
