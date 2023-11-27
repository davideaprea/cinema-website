import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { catchError } from 'rxjs';
import { NotificationService } from 'src/app/core/models/NotificationService';

@Component({
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  f!: FormGroup;
  registrationCompleted:boolean=false;
  error?:string;

  constructor(private authService: AuthService, private messageService: NotificationService) {
    this.f = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", Validators.required),
      username: new FormControl("", Validators.required),
      name: new FormControl("")
    });
  }

  showSuccess() {
    this.messageService.successMsg('Registration completed.');
  }

  submit() {
    this.authService.register(this.f.value)
    .pipe(catchError(err => this.error=err.error.message))
    .subscribe(u => {
      if(!this.error){
        this.showSuccess();
        this.registrationCompleted=true;
      }
    });
  }
}
