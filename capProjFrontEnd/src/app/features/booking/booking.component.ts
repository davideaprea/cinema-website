import { Component, OnDestroy } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { SharingBookingDataService } from './services/sharing-booking-data.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss']
})
export class BookingComponent implements OnDestroy{
  items: MenuItem[];

  constructor(private sharingBookingDataService: SharingBookingDataService) {
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

  ngOnDestroy(): void {
    this.sharingBookingDataService.reset();
  }
}
