import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: './account-verification.component.html',
  styleUrls: ['./account-verification.component.scss']
})
export class AccountVerificationComponent {
  constructor(private authService:AuthService, private route:ActivatedRoute){
    const token = this.route.snapshot.queryParamMap.get('token');
    authService.verifyEmail(token!).subscribe();
  }
}
