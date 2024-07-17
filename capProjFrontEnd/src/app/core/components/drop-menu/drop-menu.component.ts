import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { User } from 'src/app/features/auth/models/user';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { Role } from '../../models/role';

@Component({
  selector: 'app-drop-menu',
  templateUrl: './drop-menu.component.html',
  styleUrls: ['./drop-menu.component.scss']
})
export class DropMenuComponent {
  items: MenuItem[] = [];
  user: User | null = null;

  constructor(private authService: AuthService, private router: Router) {
    this.authService.user.subscribe(user => {
      this.user = user;

      this.items = [
        {
          label: 'Options',
          items: [
            {
              label: 'Profile',
              icon: 'pi pi-user',
              command: () => {
                user?.role == Role.ADMIN ? this.router.navigate(["/admin", "ourmovies"]) : this.router.navigate(["/profile", "mytickets"]);
              }
            },
            {
              label: 'Sign out',
              icon: 'pi pi-sign-out',
              command: () => {
                this.authService.logout();
              }
            }
          ]
        }
      ];
    });
  }
}
