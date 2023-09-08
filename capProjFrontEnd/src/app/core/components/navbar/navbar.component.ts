import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{
  user:IUser | null=null;
  constructor(private authService:AuthService){}
  ngOnInit(): void {
    this.authService.isUserLogged.subscribe(u=>this.user=u);
  }
}
