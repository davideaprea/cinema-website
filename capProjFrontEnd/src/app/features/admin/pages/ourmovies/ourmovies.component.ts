import { Component } from '@angular/core';
import { Movie } from '../../models/movie';
import { MovieService } from '../../services/movie.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-ourmovies',
  templateUrl: './ourmovies.component.html',
  styleUrls: ['./ourmovies.component.scss']
})
export class OurmoviesComponent {
  movies$!: Observable<Movie[]>;

  constructor(protected movieService: MovieService) {
    this.movies$=movieService.getAll();
  }
}
