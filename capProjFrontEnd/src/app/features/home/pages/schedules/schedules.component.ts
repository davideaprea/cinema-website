import { Component } from '@angular/core';
import { Movie } from 'src/app/features/admin/models/movie';
import { ScheduleService } from 'src/app/features/admin/services/schedule.service';

@Component({
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss']
})
export class SchedulesComponent {
  scheduledMovies: Movie[] = [];
  paths: string[] = [];

  constructor(private scheduleService: ScheduleService) {
    scheduleService.getScheduledMovies().subscribe(m => {
      this.scheduledMovies = m;
      this.paths = m.map(el => el.backgroundCover);
    });
  }
}
