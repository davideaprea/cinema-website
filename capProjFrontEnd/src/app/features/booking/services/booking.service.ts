import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Schedule } from '../../admin/models/schedule';
import { environment } from 'src/environments/environment.development';
import { ReceiptBody } from '../models/receipt-body';
import { Booking } from '../models/booking';
import { Receipt } from '../models/receipt';
import { User } from '../../auth/models/user';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  protected selectedSchedule?:Schedule;
  protected receipt?:ReceiptBody;

  constructor(private http:HttpClient) { }

  setReceipt(receipt:ReceiptBody){
    this.receipt=receipt;
    sessionStorage.setItem("receipt", JSON.stringify(receipt));
  }

  getReceipt(){
    if(this.receipt) return this.receipt;
    let receipt=JSON.parse(sessionStorage.getItem("receipt")!);
    if(receipt) return receipt;
  }

  setSchedule(schedule:Schedule){
    this.selectedSchedule=schedule;
    sessionStorage.setItem("schedule", JSON.stringify(schedule));
  }

  getSchedule(){
    if(this.selectedSchedule) return this.selectedSchedule;
    let selectedSchedule=JSON.parse(sessionStorage.getItem("schedule")!);
    if(selectedSchedule) return selectedSchedule;
  }

  getPrice():number{
    let receipt=this.getReceipt();
    if(receipt){
      let nSeats=receipt.bookings.length;
      let selectedRow=receipt.bookings[0].seat.nrow;
      let hallRows=receipt.bookings[0].viewSchedule.hall.nrows;
      return hallRows-selectedRow<3 ? nSeats*9.5 : nSeats*7.85;
    }
    return 0;
  }

  reset(){
    this.selectedSchedule=undefined;
    this.receipt=undefined;
    sessionStorage.removeItem("schedule");
    sessionStorage.removeItem("receipt");
  }

  getScheduleBookings(schedule:Schedule){
    return this.http.get<Booking[]>(environment.bookings+"/schedule-bookings/"+schedule.id);
  }

  getReceiptsByUser(user:User){
    return this.http.get<Receipt[]>(environment.receipts+"/all/"+user.username);
  }

  getLastMonthReceipts(){
    return this.http.get<{total: number, purchaseDate:Date}[]>(environment.receipts+"/last-month-receipts");
  }

  book(receipt:ReceiptBody){
    return this.http.post(environment.receipts, receipt);
  }
}
