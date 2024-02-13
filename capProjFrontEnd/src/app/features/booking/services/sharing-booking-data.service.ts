import { Injectable } from '@angular/core';
import { UtilityService } from 'src/app/core/services/utility.service';
import { Schedule } from '../../admin/models/schedule';
import { ReceiptBody } from '../models/receipt-body';

@Injectable({
  providedIn: 'root'
})
export class SharingBookingDataService {

  private selectedSchedule?: Schedule;
  private receiptBody?: ReceiptBody;

  constructor(private utilityService: UtilityService) { }

  set receipt(receipt: ReceiptBody | undefined) {
    if (receipt) {
      this.receiptBody = receipt;
      sessionStorage.setItem("receipt", JSON.stringify(receipt));
    }
  }

  get receipt(): ReceiptBody | undefined {
    return this.receiptBody || this.utilityService.getSessionStorageItem<ReceiptBody>("receipt");
  }

  set schedule(schedule: Schedule | undefined) {
    if (schedule) {
      this.selectedSchedule = schedule;
      sessionStorage.setItem("schedule", JSON.stringify(schedule));
    }
  }

  get schedule(): Schedule | undefined {
    return this.selectedSchedule || this.utilityService.getSessionStorageItem<Schedule>("schedule");
  }

  getPrice(): number {
    let receipt = this.receipt;
    if (receipt) {
      let nSeats = receipt.bookings.length;
      let selectedRow = receipt.bookings[0].seat.nrow;
      let hallRows = receipt.bookings[0].viewSchedule.hall.nrows;
      return hallRows - selectedRow < 3 ? nSeats * 9.5 : nSeats * 7.85;
    }
    return 0;
  }

  reset() {
    this.selectedSchedule = undefined;
    this.receiptBody = undefined;
    sessionStorage.removeItem("schedule");
    sessionStorage.removeItem("receipt");
  }
}
