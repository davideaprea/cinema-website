import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from 'src/app/features/admin/models/movie';
import { ScheduleService } from 'src/app/features/admin/services/schedule.service';

@Component({
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss']
})
export class SchedulesComponent {
  scheduledMovies$!: Observable<Movie[]>;

  constructor(private scheduleService: ScheduleService) {
    this.scheduledMovies$=scheduleService.getScheduledMovies();
  }
}
