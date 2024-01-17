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
  allMovies$!: Observable<Movie[]>;
  availableHalls$!: Observable<Hall[]>;
  f: FormGroup;

  constructor(private movieService: MovieService, private hallService: HallService, private scheduleService: ScheduleService, private messageService: NotificationService) {
    this.allMovies$ = movieService.getAll();
    this.availableHalls$ = hallService.getAvailableHalls();

    this.f = new FormGroup({
      movie: new FormControl(null, Validators.required),
      hall: new FormControl(null, Validators.required),
      startTime: new FormControl(null, Validators.required),
    });
  }

  submit() {
    this.scheduleService.create(this.f.value).subscribe(d => {
      this.messageService.successMsg('Schedule added successfully.');
      this.f.reset();
    });
  }
}
