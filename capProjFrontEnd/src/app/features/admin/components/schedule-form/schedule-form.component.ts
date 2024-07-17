import { Component } from '@angular/core';
import { Movie } from '../../models/movie';
import { MovieService } from '../../services/movie.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HallService } from '../../services/hall.service';
import { Hall } from '../../models/hall';
import { ScheduleService } from '../../services/schedule.service';
import { NotificationService } from 'src/app/core/services/notification.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-schedule-form',
  templateUrl: './schedule-form.component.html',
  styleUrls: ['./schedule-form.component.scss']
})
export class ScheduleFormComponent {
  allMovies$: Observable<Movie[]> = this.movieService.getAll();
  availableHalls$: Observable<Hall[]> = this.hallService.getAvailableHalls();
  f: FormGroup = new FormGroup({
    movie: new FormControl(null, Validators.required),
    hall: new FormControl(null, Validators.required),
    startTime: new FormControl(null, Validators.required),
  });

  constructor(
    private movieService: MovieService,
    private hallService: HallService,
    private scheduleService: ScheduleService,
    private messageService: NotificationService
  ) { }

  submit() {
    this.scheduleService.create(this.f.value).subscribe(() => {
      this.messageService.successMsg('Schedule added successfully.');
      this.f.reset();
    });
  }
}
