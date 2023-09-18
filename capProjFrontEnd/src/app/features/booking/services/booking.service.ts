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

  constructor(private http:HttpClient) { }

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

  getScheduleBookings(id:number){
    return this.http.get<Booking[]>(environment.bookings+"/schedule-bookings/"+id);
  }
}
