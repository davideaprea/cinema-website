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

  constructor(private http:HttpClient) { }

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
