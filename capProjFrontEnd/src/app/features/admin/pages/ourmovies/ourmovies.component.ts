import { Component } from '@angular/core';
import { Movie } from '../../models/movie';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-ourmovies',
  templateUrl: './ourmovies.component.html',
  styleUrls: ['./ourmovies.component.scss']
})
export class OurmoviesComponent {
  movies: Movie[] = [];

  constructor(private movieService: MovieService) {
    movieService.getAll().subscribe(m => this.movies = m);
  }
}
