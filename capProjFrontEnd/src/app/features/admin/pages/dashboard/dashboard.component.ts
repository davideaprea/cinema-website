import { Component } from '@angular/core';
import { BookingService } from 'src/app/features/booking/services/booking.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  lastMonthPurchases:{total: number, purchaseDate:Date}[]=[];

  constructor(private receiptService:BookingService){
    receiptService.getLastMonthReceipts().subscribe(data => this.lastMonthPurchases=data);
  }
}
