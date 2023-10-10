import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { ILogUser } from '../../models/ilog-user';

@Component({
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  f!:FormGroup;
  wrongCredentials=false;

  constructor(private authService:AuthService){
    this.f=new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", Validators.required),
      remember: new FormControl<boolean>(false)
    });
  }

  ngOnInit(): void {}

  submit(){
    const username:string=this.f.value.email;
    const password:string=this.f.value.password;
    const remember:boolean=this.f.value.remember[0];

    const credentials:ILogUser={
      username: username,
      password: password
    }

    this.authService.login(credentials, remember);
  }
}
