import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MovieBody } from '../models/movie-body';
import { environment } from 'src/environments/environment.development';
import { Genres } from '../models/genres';
import { Movie } from '../models/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  cover: string = "";

  constructor(private http: HttpClient) { }

  create(movie: MovieBody) {
    const formData = new FormData();
    formData.append('title', movie.title);
    formData.append('trailerLink', movie.trailerLink);
    formData.append('releaseDate', movie.releaseDate.toISOString().slice(0, 10));
    formData.append('duration', movie.duration.toString());
    formData.append('director', movie.director);
    formData.append('actors', movie.actors);
    formData.append('description', movie.description);
    formData.append('isTridimensional', movie.isTridimensional ? 'true' : 'false');

    movie.genres.forEach((genre: string) => {
      const index = Object.values(Genres).indexOf(genre as unknown as Genres);
      const key = Object.keys(Genres)[index];
      formData.append("genres", key);
    });

    formData.append('cover', movie.cover);
    this.http.post(environment.movies, formData).subscribe(res => console.log(res));
  }

  get(id: number){
    return this.http.get<Movie>(environment.movies + "/" + id);
  }

  getAll(){
    return this.http.get<Movie[]>(environment.movies);
  }
}
