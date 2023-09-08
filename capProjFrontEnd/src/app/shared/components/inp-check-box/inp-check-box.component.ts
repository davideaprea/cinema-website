import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-inp-check-box',
  templateUrl: './inp-check-box.component.html',
  styleUrls: ['./inp-check-box.component.scss']
})
export class InpCheckBoxComponent {
  @Input() prop!:string;
  @Input() control!:FormControl;
  @Input() group!:FormGroup;
}
