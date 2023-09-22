import { Component } from '@angular/core';
import { Hall } from '../../models/hall';
import { HallService } from '../../services/hall.service';

@Component({
  selector: 'app-halls',
  templateUrl: './halls.component.html',
  styleUrls: ['./halls.component.scss']
})
export class HallsComponent {
  halls:Hall[]=[];

  constructor(private hallService:HallService){
    hallService.getAll().subscribe(h=>this.halls=h);
  }
}
