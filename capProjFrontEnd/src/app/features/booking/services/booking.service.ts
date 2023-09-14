import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Schedule } from '../../admin/models/schedule';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  protected selectedSchedule?:Schedule;

  constructor(private http:HttpClient) { }

  setSchedule(schedule:Schedule){
    this.selectedSchedule=schedule;
    sessionStorage.setItem("schedule", JSON.stringify(schedule));
  }

  getSchedule(){
    if(this.selectedSchedule) return this.selectedSchedule;
    let selectedSchedule=JSON.parse(sessionStorage.getItem("schedule")!);
    if(selectedSchedule) return selectedSchedule;
  }
}
