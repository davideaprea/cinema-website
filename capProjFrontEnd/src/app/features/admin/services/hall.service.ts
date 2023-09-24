import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Hall } from '../models/hall';

@Injectable({
  providedIn: 'root'
})
export class HallService {

  constructor(private http:HttpClient) { }

  create(nrows:number, nseatsPerRow:number){
    console.log(nrows, nseatsPerRow)
    return this.http.post(environment.halls, {nrows, nseatsPerRow});
  }

  getAll(){
    return this.http.get<Hall[]>(environment.halls);
  }

  getAvailableHalls(){
    return this.http.get<Hall[]>(environment.halls+"/available-halls");
  }

  editHall(hall:Hall){
    return this.http.put<Hall>(environment.halls+"/"+hall.id, hall);
  }
}
