import { Component } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  dataArr=[
    {icon: "pi pi-video", route: "ourmovies", text: "Our movies"},
    {icon: "pi pi-upload", route: "addmovie", text: "Add a new movie"},
    {icon: "pi pi-calendar", route: "schedules", text: "Schedules"},
    {icon: "pi pi-desktop", route: "halls", text: "Halls"},
    {icon: "pi pi-chart-bar", route: "", text: "Dashboard"},
    {icon: "pi pi-users", route: "", text: "Admins & mods"},
  ];

  showMenu=false;

  constructor(protected authService:AuthService){}

  toggleShow(){
    this.showMenu=!this.showMenu;
  }
}
