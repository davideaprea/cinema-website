import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Schedule } from '../models/schedule';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor(private http:HttpClient) { }

  create(schedule:Schedule){
    return this.http.post(environment.schedules, schedule);
  }
}
