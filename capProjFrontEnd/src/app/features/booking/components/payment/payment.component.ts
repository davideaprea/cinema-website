import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { BookingService } from '../../services/booking.service';
import { Schedule } from 'src/app/features/admin/models/schedule';
import { ReceiptBody } from '../../models/receipt-body';
import { environment } from 'src/environments/environment.development';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { IUser } from 'src/app/core/models/iuser';
import { Router } from '@angular/router';

declare var paypal: any;

@Component({
  selector: "app-payment",
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {
  @ViewChild('paypal', { static: true }) paypalElement!: ElementRef;

  private user: IUser | null = null;
  selectedSchedule!: Schedule;
  receipt!: ReceiptBody;
  price!: number;
  error: string = "";

  constructor(private bookingService: BookingService, private authService:AuthService, private router:Router) {
    authService.isUserLogged.subscribe(u=>this.user=u);

    this.selectedSchedule = bookingService.getSchedule();
    this.receipt = bookingService.getReceipt();
  }

  ngOnInit(): void {
    let payPalButtons=paypal
      .Buttons({
        createOrder: (data:any) => {
          return fetch(environment.payments, {
            method: "POST",
            headers: {
              "Authorization": "Bearer "+this.user!.accessToken,
              "Content-Type": "application/json"
            },
            body: JSON.stringify(this.receipt),
          })
            .then(res => res.text())
            .then(id=> id)
        },
        onApprove: (data:any, actions:any) => {
          console.log(data)
          return fetch(environment.payments+`/${data.orderID}`, {
            method: "POST",
            headers: {
              "Authorization": "Bearer "+this.user!.accessToken,
              "Content-Type": "application/json"
            }
          })
            .then((response) => response.json())
            .then(json=> json)
        }
      })
      .render(this.paypalElement.nativeElement);
  }
}
