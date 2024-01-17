import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { MenuItem } from 'primeng/api';
import { User } from 'src/app/features/auth/models/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  user: User | null = null;

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
    this.authService.user.subscribe(u => this.user = u);
  }
}
