import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { MessageService } from 'primeng/api';

@Component({
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  providers: [MessageService]
})
export class RegisterComponent {
  f!: FormGroup;
  registrationCompleted:boolean=false;

  constructor(private authService: AuthService, private messageService: MessageService) {
    this.f = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", Validators.required),
      username: new FormControl("", Validators.required),
      name: new FormControl("")
    });
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Registration completed.' });
  }

  submit() {
    this.authService.register(this.f.value)
    .subscribe(u => {
      this.showSuccess();
      this.registrationCompleted=true;
    });
  }
}
