import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Schedule } from '../models/schedule';
import { environment } from 'src/environments/environment.development';
import { Movie } from '../models/movie';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor(private http:HttpClient) { }

  create(schedule:Schedule){
    return this.http.post<Schedule>(environment.schedules, schedule);
  }

  getScheduledMovies(){
    return this.http.get<Movie[]>(environment.schedules+"/scheduled-movies");
  }

  getMovieSchedules(movie:Movie){
    return this.http.get<Schedule[]>(environment.schedules+"/movie-schedules/"+movie.id);
  }

  getNextSchedules(){
    return this.http.get<Schedule[]>(environment.schedules+"/next-schedules");
  }

  getCurrentSchedules(){
    return this.http.get<Schedule[]>(environment.schedules+"/current-schedules");
  }

  edit(schedule:Omit<Schedule, "endTime">){
    return this.http.put<Schedule>(environment.schedules+"/"+schedule.id, schedule);
  }

  delete(ids:number[]){
    return this.http.delete(environment.schedules, {body: ids});
  }
}
