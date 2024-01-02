import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { ILogUser } from '../../models/ilog-user';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent{
  f!:FormGroup;
  wrongCredentials=false;

  constructor(private authService:AuthService){
    this.f=new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", Validators.required),
      remember: new FormControl<boolean>(false)
    });
  }

  submit(){
    const credentials:ILogUser={
      username: this.f.value.email,
      password: this.f.value.password
    }

    this.authService.login(credentials, this.f.value.remember[0]).subscribe(
      {
        error: e => {
          this.wrongCredentials=true;
          this.f.reset();
        }
      }
    );
  }
}
