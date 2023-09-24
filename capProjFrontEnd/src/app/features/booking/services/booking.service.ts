import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Schedule } from '../../admin/models/schedule';
import { Receipt } from '../models/receipt';
import { environment } from 'src/environments/environment.development';
import { ReceiptBody } from '../models/receipt-body';
import { Booking } from '../models/booking';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  protected selectedSchedule?:Schedule;
  protected receipt?:Receipt;

  constructor(private http:HttpClient) { }

  setReceipt(receipt:Receipt){
    this.receipt=receipt;
    sessionStorage.setItem("receipt", JSON.stringify(receipt));
  }

  getReceipt(){
    if(this.receipt) return this.selectedSchedule;
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

  bookSeats(receipt:ReceiptBody){
    return this.http.post(environment.receipts, receipt);
  }

  getScheduleBookings(schedule:Schedule){
    return this.http.get<Booking[]>(environment.bookings+"/schedule-bookings/"+schedule.id);
  }

  createOrder(receipt:ReceiptBody){
    console.log(receipt)
    return this.http.post(environment.paypal, receipt);
  }
}
