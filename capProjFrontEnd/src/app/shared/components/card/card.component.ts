import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {
  @Input() title!:string;
  @Input() director!:string;
  @Input() cover!:number[];
  @Input() id!:number;
}
