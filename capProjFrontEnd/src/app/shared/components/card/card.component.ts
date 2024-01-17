import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Genres } from 'src/app/features/admin/models/genres';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {
  @Input() title!:string;
  @Input() director!:string;
  @Input() cover!:any;
  @Input() id!:number;
  @Input() genres!:Genres[]

  constructor(public router:Router){}
}
