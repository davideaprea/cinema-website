import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { HallService } from '../../services/hall.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-hall-form',
  templateUrl: './hall-form.component.html',
  styleUrls: ['./hall-form.component.scss'],
  providers: [MessageService]
})
export class HallFormComponent {
  f!:FormGroup;

  constructor(private hallService:HallService, protected authService:AuthService, private messageService:MessageService){
    this.f=new FormGroup({
      rows: new FormControl(null, Validators.required),
      seats: new FormControl(null, Validators.required)
    });
  }

  submit(){
    this.hallService.create(this.f.value.rows, this.f.value.seats).subscribe(res=>{
      this.f.reset();
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Hall added successfully.' });
    });
  }
}
