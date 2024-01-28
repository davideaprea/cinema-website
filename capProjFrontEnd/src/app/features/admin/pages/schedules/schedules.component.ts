import { Component } from '@angular/core';
import { Schedule } from '../../models/schedule';
import { Table } from 'primeng/table';
import { ScheduleService } from '../../services/schedule.service';
import { Movie } from '../../models/movie';
import { Hall } from '../../models/hall';
import { HallService } from '../../services/hall.service';
import { MovieService } from '../../services/movie.service';
import { ConfirmationService } from 'primeng/api';
import { NotificationService } from 'src/app/core/services/notification.service';
import { PTableHeader } from '../../models/ptable-header';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss'],
  providers: [ConfirmationService]
})
export class SchedulesComponent {
  schedules: Schedule[] = [];
  movies: Movie[] = [];
  halls: Hall[]= [];
  loading = true;
  editedSchedule?: Schedule;
  today = new Date();
  schedulesToDelete: Schedule[] = [];
  readonly tableHeaders: PTableHeader[]=[
    { type: "text", field: "movie.title", name: "Movie" },
    { type: "text", field: "hall.id", name: "Hall n°" },
    { type: "date", field: "startTime", name: "Start time" },
    { type: "date", field: "endTime", name: "End time" },
  ];

  constructor(private confirmationService: ConfirmationService, private scheduleService: ScheduleService, private movieService: MovieService, private hallService: HallService, private messageService: NotificationService) {
    this.scheduleService.getNextSchedules().subscribe(schedules => {
      schedules.forEach(el => {
        el.startTime = new Date(<Date>el.startTime);
        el.endTime = new Date(<Date>el.endTime);
      });

      this.schedules = schedules;
      this.loading = false;
    });
  }

  clear(table: Table) {
    table.clear();
  }

  onRowEditInit(schedule: Schedule) {
    if (this.movies.length==0) this.movieService.getAll().subscribe(m => this.movies = m);
    if (this.halls.length==0) this.hallService.getAll().subscribe(h => this.halls=h);
    this.editedSchedule = { ...schedule };
  }

  onRowEditSave(schedule: Schedule) {
    if (JSON.stringify(this.editedSchedule) !== JSON.stringify(schedule)) {
      delete schedule.endTime;
      this.scheduleService.edit(schedule).subscribe(res => {
        this.handleRowActions(res, "Schedule edited successfully.");
        this.editedSchedule = undefined;
      });
    }
  }

  onRowEditCancel() {
    this.editedSchedule = undefined;
  }

  schedulesRemovalConfirmation() {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete these schedules?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        const ids=this.schedulesToDelete.map(schedule => schedule.id);
        this.scheduleService.delete(ids).subscribe(res => {
          for (let scToDelete of ids) {
            let i = this.schedules.findIndex(sc => sc.id == scToDelete);
            this.schedules.splice(i, 1);
          }

          this.schedulesToDelete = [];

          this.messageService.successMsg('Schedules deleted successfully.');
        })
      }
    })
  }

  private handleRowActions(schedule: Schedule, msg: string){
    let i = this.schedules.findIndex(sc => sc.id == schedule.id);
    this.schedules[i] = schedule;
    this.messageService.successMsg(msg);
  }
}
