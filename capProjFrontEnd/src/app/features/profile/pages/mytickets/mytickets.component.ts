import { Component } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { Receipt } from 'src/app/features/booking/models/receipt';
import { BookingService } from 'src/app/features/booking/services/booking.service';

@Component({
  selector: 'app-mytickets',
  templateUrl: './mytickets.component.html',
  styleUrls: ['./mytickets.component.scss']
})
export class MyticketsComponent {
  userReceipts:Receipt[]=[];

  constructor(private bookingService:BookingService, private authService:AuthService){
    authService.isUserLogged.subscribe(user=>{
      bookingService.getReceiptsByUser(user!).subscribe(receipts=>{
        receipts.forEach(el => el.bookings[0].viewSchedule.startTime = new Date(el.bookings[0].viewSchedule.startTime));
        receipts.sort((a, b) => a.bookings[0].viewSchedule.startTime.getTime() - b.bookings[0].viewSchedule.startTime.getTime());
        this.userReceipts=receipts;
      });
    });
  }
}
