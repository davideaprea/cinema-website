import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-inp-date-time',
  templateUrl: './inp-date-time.component.html',
  styleUrls: ['./inp-date-time.component.scss']
})
export class InpDateTimeComponent {
  @Input() prop!:string;
  @Input() control!:FormControl;
  @Input() group!:FormGroup;

  today:Date;
  constructor(){
    this.today=new Date();
  }
}
