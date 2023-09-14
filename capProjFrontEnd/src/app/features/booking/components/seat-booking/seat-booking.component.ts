import { Component } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { Schedule } from 'src/app/features/admin/models/schedule';

@Component({
  templateUrl: './seat-booking.component.html',
  styleUrls: ['./seat-booking.component.scss']
})
export class SeatBookingComponent {
  schedule!:Schedule;
  rows!:number[];
  seats!:number[];

  constructor(private bookingService:BookingService){
    this.schedule=bookingService.getSchedule();
    this.rows=Array(this.schedule.hall.nrows).fill(0);
    this.seats=Array(this.schedule.hall.nseatsPerRow).fill(0);
    console.log(this.schedule);
  }
}
