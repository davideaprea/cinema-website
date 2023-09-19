import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Movie } from 'src/app/features/admin/models/movie';
import { Schedule } from 'src/app/features/admin/models/schedule';
import { MovieService } from 'src/app/features/admin/services/movie.service';
import { ScheduleService } from 'src/app/features/admin/services/schedule.service';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { BookingService } from '../../../booking/services/booking.service';

@Component({
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent {
  movie!: Movie;
  schedules: Schedule[] = [];
  selectedSchedules: Schedule[] = [];
  items: MenuItem[] = [];
  activeItem!: MenuItem;
  isUserAdmin: boolean;

  constructor(
    private route: ActivatedRoute,
    private movieService: MovieService,
    private scheduleService: ScheduleService,
    private authService: AuthService,
    private bookingService: BookingService) {
    this.isUserAdmin = authService.isUserAdmin();

    const id = Number(route.snapshot.paramMap.get("id"));
    movieService.get(id).subscribe(m => {
      this.movie = m;

      scheduleService.getMovieSchedules(this.movie).subscribe(s => {
        this.schedules = s;
        for (let schedule of this.schedules) {
          let date = new Date(schedule.startTime);
          let item: MenuItem = {
            label: date.toLocaleDateString()
          }

          this.items.push(item);
        }
        this.activeItem = this.items[0];
        this.onActiveItemChange(this.activeItem);
      });
    });
  }

  onActiveItemChange(event: MenuItem) {
    this.selectedSchedules = this.schedules.filter(s => {
      let date = new Date(s.startTime).toLocaleDateString();
      return date == event.label;
    });
  }

  saveSchedule(schedule: Schedule) {
    this.bookingService.setSchedule(schedule);
  }
}
