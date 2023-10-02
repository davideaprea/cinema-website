import { Component } from '@angular/core';
import { Schedule } from '../../models/schedule';
import { Table } from 'primeng/table';
import { ScheduleService } from '../../services/schedule.service';
import { Movie } from '../../models/movie';
import { Hall } from '../../models/hall';
import { HallService } from '../../services/hall.service';
import { MovieService } from '../../services/movie.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.scss'],
  providers: [ConfirmationService, MessageService]
})
export class SchedulesComponent {
  schedules: Schedule[] = [];
  movies: Movie[] = [];
  halls: Hall[] = [];
  loading = true;
  editedSchedule?: Schedule;
  today?: Date;
  schedulesToDelete: number[] = [];

  constructor(private confirmationService: ConfirmationService, private scheduleService: ScheduleService, private movieService: MovieService, private hallService: HallService, private messageService: MessageService) {
    this.loadSchedules();
  }

  loadSchedules(){
    this.scheduleService.getNextSchedules().subscribe(schedules => {
      schedules.forEach(el => {
        el.startTime = new Date(<Date>el.startTime);
        el.endTime = new Date(<Date>el.endTime);
      });

      schedules.sort((a, b) => a.startTime.getTime() - b.startTime.getTime());
      this.schedules = schedules;
      this.loading = false;
    })
  }

  clear(table: Table) {
    table.clear();
  }

  onRowEditInit(schedule: Schedule) {
    if (this.movies.length == 0) this.movieService.getAll().subscribe(m => this.movies = m);
    if (this.halls.length == 0) this.hallService.getAll().subscribe(h => this.halls = h);
    this.today = new Date();
    this.editedSchedule = { ...schedule };
  }

  onRowEditSave(schedule: Schedule) {
    if (JSON.stringify(this.editedSchedule) !== JSON.stringify(schedule)) {
      delete schedule.endTime;
      this.scheduleService.edit(schedule).subscribe(r => {
        this.editedSchedule = undefined;
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Schedule edited successfully.' });
        this.loadSchedules();
      });
    }
  }

  onRowEditCancel() {
    this.editedSchedule = undefined;
  }

  addScheduleToDelete(schedule: Schedule) {
    this.schedulesToDelete.push(schedule.id);
  }

  removeScheduleToDelete(schedule: Schedule) {
    let index = this.schedulesToDelete.findIndex(id => id == schedule.id);
    this.schedulesToDelete.splice(index, 1);
  }

  schedulesRemovalConfirmation() {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete these schedules?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.scheduleService.delete(this.schedulesToDelete).subscribe(res=>{
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Schedules deleted successfully.' });
          this.loadSchedules();
        })
      }
    })
  }
}
