import { HttpEvent } from '@angular/common/http';
import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Movie } from 'src/app/features/admin/models/movie';
import { MovieService } from 'src/app/features/admin/services/movie.service';
import { ScheduleService } from 'src/app/features/admin/services/schedule.service';

@Component({
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss']
})
export class SchedulesComponent {
  scheduledMovies:Movie[]=[];
  cover!:any;
  constructor(private scheduleService:ScheduleService, private movieService:MovieService, public sanitizer: DomSanitizer){
    scheduleService.getScheduledMovies().subscribe(m=>{
      this.scheduledMovies=m;
      console.log(m);
    });
    this.getCover();
  }

  getCover(){
    this.movieService.getCover("2c0d71e9-9875-4fe6-88d0-3fa834198c34_oppenheimer-cover.jpg").subscribe(c=>{
      this.cover=URL.createObjectURL(c.body!);
      console.log(c);
    });
  }
}
