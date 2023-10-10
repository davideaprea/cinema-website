import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { IUser } from '../../models/iuser';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  user: IUser | null = null;

  items: MenuItem[] = [
    {
      label: 'Go to:',
      items: [
        {
          label: 'Home',
          icon: 'pi pi-home',
          command: () => {
          }
        },
        {
          label: 'Schedules',
          icon: 'pi pi-calendar',
          command: () => {
          }
        },
        {
          label: 'Movies',
          icon: 'pi pi-video',
          command: () => {
          }
        },
        {
          label: 'Promotions',
          icon: 'pi pi-ticket',
          command: () => {
          }
        }
      ]
    }
  ];

  constructor(private authService: AuthService) { }
  ngOnInit(): void {
    this.authService.isUserLogged.subscribe(u => this.user = u);
  }
}
