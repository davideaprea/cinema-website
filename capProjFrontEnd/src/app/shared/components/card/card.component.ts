import { Component, Input } from '@angular/core';
import { SafeUrl } from '@angular/platform-browser';
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
}
