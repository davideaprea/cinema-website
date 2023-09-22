import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Genres } from 'src/app/features/admin/models/genres';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.scss']
})
export class MovieFormComponent {
  columnStyles="col-12 sm:col-6 lg:col-4 xl:col-3";

  f!:FormGroup;
  today:Date;
  genres:Genres[];

  constructor(private movieService:MovieService){
    this.today=new Date();
    this.genres=Object.values(Genres);
    this.f=new FormGroup({
      title: new FormControl("", Validators.required),
      cover: new FormControl(null, Validators.required),
      trailerLink: new FormControl(""),
      releaseDate: new FormControl("", Validators.required),
      duration: new FormControl("", Validators.required),
      director: new FormControl("", Validators.required),
      actors: new FormControl("", Validators.required),
      description: new FormControl("", [Validators.required, Validators.maxLength(600)]),
      genres: new FormControl("", Validators.required),
      isTridimensional: new FormControl(false)
    });
  }

  uploadCover(event:any){
    this.f.controls['cover'].setValue(event.currentFiles[0]);
  }

  submit(){
    this.movieService.create(this.f.value);
  }
}
