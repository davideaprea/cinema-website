import { AfterViewInit, Component, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Movie } from 'src/app/features/admin/models/movie';
import { Schedule } from 'src/app/features/admin/models/schedule';
import { MovieService } from 'src/app/features/admin/services/movie.service';
import { ScheduleService } from 'src/app/features/admin/services/schedule.service';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { BookingService } from '../../../booking/services/booking.service';
import { BehaviorSubject, filter, switchMap, tap, zip } from 'rxjs';
import { User } from 'src/app/features/auth/models/user';

@Component({
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent implements AfterViewInit {
  private loading$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);
  movie!: Movie;
  schedules: Schedule[] = [];
  selectedSchedules: Schedule[] = [];
  items: MenuItem[] = [];
  activeItem!: MenuItem;
  user!: User | null;
  beforeBgColor!: string;

  constructor(private renderer: Renderer2, private host: ElementRef, private route: ActivatedRoute, private movieService: MovieService, private scheduleService: ScheduleService, private authService: AuthService, private bookingService: BookingService) {
    const id = Number(route.snapshot.paramMap.get("id"));
    zip(
      authService.user,
      movieService.get(id).pipe(
        tap(movie => this.movie = movie),
        switchMap(movie => scheduleService.getMovieSchedules(movie))
      )
    ).subscribe(res => {
      this.user = res[0]!;

      this.schedules = res[1];
      this.schedules.forEach(schedule => {
        schedule.startTime = new Date(schedule.startTime);
        let date = schedule.startTime.toLocaleDateString();
        if (!this.items.some(item => item.label == date)) this.items.push({ label: date });
      });

      this.activeItem = this.items[0];
      this.onActiveItemChange(this.activeItem);
      this.loading$.next(false);
    }
    );
  }

  ngAfterViewInit(): void {
    this.loading$
    .pipe(filter(loading => !loading))
    .subscribe(() => this.renderer.setStyle(this.host.nativeElement, 'background-image', `url(${this.movie.backgroundCover})`));
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

  trackByFn(index: number, name: Schedule): number {
    return name.id;
  }
}
