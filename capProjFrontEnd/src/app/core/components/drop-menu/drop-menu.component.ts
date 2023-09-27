import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-drop-menu',
  templateUrl: './drop-menu.component.html',
  styleUrls: ['./drop-menu.component.scss']
})
export class DropMenuComponent implements OnInit {
  items: MenuItem[] | undefined;
  user: IUser | null = null;

  constructor(private authService: AuthService, private router: Router) {
    this.authService.isUserLogged.subscribe(user => {
      this.user=user;
      this.items = [
        {
          label: 'Options',
          items: [
            {
              label: 'Profile',
              icon: 'pi pi-user',
              command: () => {
                this.authService.isUserAdmin(user) ? this.router.navigate(["/admin", "ourmovies"]) : this.router.navigate(["/profile", "mytickets"]);
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

  ngOnInit(): void {

  }
}
