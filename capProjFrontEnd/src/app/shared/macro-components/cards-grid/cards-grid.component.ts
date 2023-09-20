import { Component, Input } from '@angular/core';
import { Movie } from 'src/app/features/admin/models/movie';

@Component({
  selector: 'app-cards-grid',
  templateUrl: './cards-grid.component.html',
  styleUrls: ['./cards-grid.component.scss']
})
export class CardsGridComponent {
  @Input() movieArr!:Movie[];
}
