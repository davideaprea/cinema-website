import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-inp-text',
  templateUrl: './inp-text.component.html',
  styleUrls: ['./inp-text.component.scss']
})
export class InpTextComponent {
  @Input() prop!:string;
  @Input() control!:FormControl;
  @Input() group!:FormGroup;
}
