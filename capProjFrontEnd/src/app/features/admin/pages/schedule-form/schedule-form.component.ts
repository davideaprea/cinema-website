import { Component } from '@angular/core';
import { Movie } from '../../models/movie';
import { MovieService } from '../../services/movie.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HallService } from '../../services/hall.service';
import { Hall } from '../../models/hall';
import { ScheduleService } from '../../services/schedule.service';

@Component({
  selector: 'app-schedule-form',
  templateUrl: './schedule-form.component.html',
  styleUrls: ['./schedule-form.component.scss']
})
export class ScheduleFormComponent {
  movies:Movie[]=[];
  halls:Hall[]=[];
  f:FormGroup;

  constructor(private movieService:MovieService, private hallService:HallService, private scheduleService:ScheduleService){
    movieService.getAll().subscribe(m=>this.movies=m);
    hallService.getAll().subscribe(h=>this.halls=h);

    this.f=new FormGroup({
      movie:new FormControl(null, Validators.required),
      hall:new FormControl(null, Validators.required),
      startTime:new FormControl(null, Validators.required),
    });
  }

  submit(){
    this.scheduleService.create(this.f.value).subscribe(d=>console.log(d));
  }
}
