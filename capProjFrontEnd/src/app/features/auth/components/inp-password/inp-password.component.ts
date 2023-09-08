import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inp-password',
  templateUrl: './inp-password.component.html',
  styleUrls: ['./inp-password.component.scss']
})
export class InpPasswordComponent {
  regPage:boolean=false;
  @Input() group!: FormGroup;

  constructor(private route:Router){
    if(route.url.includes("/auth/register")) this.regPage=true;
  }
}
