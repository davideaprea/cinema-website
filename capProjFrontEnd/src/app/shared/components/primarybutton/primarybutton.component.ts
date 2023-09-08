import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-primarybutton',
  templateUrl: './primarybutton.component.html',
  styleUrls: ['./primarybutton.component.scss']
})
export class PrimarybuttonComponent {
  @Input() label!: string;
  @Input() styleClass!: string;
}
