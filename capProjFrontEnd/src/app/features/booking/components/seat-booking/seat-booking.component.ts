import { Component } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { Schedule } from 'src/app/features/admin/models/schedule';
import { ReceiptBody } from '../../models/receipt-body';
import { BookingBody } from '../../models/booking-body';
import { SeatStatus } from './seat-status';
import { Booking } from '../../models/booking';

@Component({
  selector: "app-seat-booking",
  templateUrl: './seat-booking.component.html',
  styleUrls: ['./seat-booking.component.scss']
})
export class SeatBookingComponent {
  schedule!: Schedule;
  seats!: SeatStatus[][];
  bookings: BookingBody[] = [];
  bookedSeats: Booking[] = [];
  onSelection = false;

  constructor(private bookingService: BookingService) {
    this.schedule = bookingService.getSchedule();
    bookingService.getScheduleBookings(this.schedule).subscribe(bookings => {
      this.bookedSeats = bookings;
      console.log(bookings)
      this.restoreSeats();
    });
  }

  manageSeats(row: number, seat: number) {
    if (!this.onSelection) {
      for (let i = 0; i < this.seats.length; i++) {
        for (let j = 0; j < this.seats[i].length; j++) {
          if (this.seats[i][j] == SeatStatus.BOOKED) continue;
          this.seats[i][j] = SeatStatus.NOT_AVAILABLE;
        }
      }
      this.seats[row][seat] = SeatStatus.SELECTED;
      if (this.seats[row][seat + 1] && this.seats[row][seat + 1] != SeatStatus.BOOKED) this.seats[row][seat + 1] = SeatStatus.AVAILABLE;
      if (this.seats[row][seat - 1] && this.seats[row][seat - 1] != SeatStatus.BOOKED) this.seats[row][seat - 1] = SeatStatus.AVAILABLE;

      this.addBooking(row, seat);
      this.onSelection = true;
    }
    else {
      if (this.seats[row][seat] == SeatStatus.AVAILABLE) {
        this.seats[row][seat] = SeatStatus.SELECTED;
        if (this.seats[row][seat + 1] == SeatStatus.NOT_AVAILABLE) this.seats[row][seat + 1] = SeatStatus.AVAILABLE;
        else if (this.seats[row][seat - 1] == SeatStatus.NOT_AVAILABLE) this.seats[row][seat - 1] = SeatStatus.AVAILABLE;
        this.addBooking(row, seat);
      }
      else if (this.seats[row][seat] == SeatStatus.SELECTED) {
        if (this.bookings.length == 1) {
          this.restoreSeats();
          this.onSelection = false;
          this.removeBooking(row, seat);
        }
        else if (this.seats[row][seat - 1] == SeatStatus.AVAILABLE || this.seats[row][seat - 1] == undefined) {
          this.seats[row][seat] = SeatStatus.AVAILABLE;
          if (this.seats[row][seat - 1]) this.seats[row][seat - 1] = SeatStatus.NOT_AVAILABLE;
          this.removeBooking(row, seat);
        }
        else if (this.seats[row][seat + 1] == SeatStatus.AVAILABLE || this.seats[row][seat + 1] == undefined) {
          this.seats[row][seat] = SeatStatus.AVAILABLE;
          if (this.seats[row][seat + 1]) this.seats[row][seat + 1] = SeatStatus.NOT_AVAILABLE;
          this.removeBooking(row, seat);
        }
      }
    }
  }

  private restoreSeats() {
    this.seats = Array(this.schedule.hall.nrows)
      .fill(SeatStatus.AVAILABLE)
      .map(() => Array(this.schedule.hall.nseatsPerRow).fill(SeatStatus.AVAILABLE));

    for (let booking of this.bookedSeats) this.seats[booking.seat.nrow - 1][booking.seat.nseat - 1] = SeatStatus.BOOKED;
  }

  private removeBooking(row: number, seat: number) {
    let i = this.bookings.findIndex(booking => booking.seat.nrow == row + 1 && booking.seat.nseat == seat + 1)
    this.bookings.splice(i, 1);
    console.log(this.bookings.map(b=>b.seat))
  }

  private addBooking(row: number, seat: number) {
    let booking: BookingBody = {
      viewSchedule: this.schedule,
      seat: {
        nseat: seat + 1,
        nrow: row + 1
      }
    }
    this.bookings.push(booking);
    console.log(this.bookings.map(b=>b.seat))
  }

  createReceipt() {
    const receipt: ReceiptBody = {
      user: JSON.parse(sessionStorage.getItem("user")!),
      bookings: this.bookings
    }

    this.bookingService.setReceipt(receipt);
  }
}
