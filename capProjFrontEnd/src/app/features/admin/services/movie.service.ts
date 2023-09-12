import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../models/movie';
import { environment } from 'src/environments/environment.development';
import { Genres } from '../models/genres';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http: HttpClient) { }

  create(movie: Movie) {
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

    console.log(formData);
    this.http.post(environment.movies, formData).subscribe(res => console.log(res));
  }

  /* creatTest(cover: File) {
    const formData = new FormData();
    formData.append('cover', cover);

    console.log(cover);
    this.http.post("http://localhost:8080/movies/uploadtest", formData).subscribe(res => console.log(res));
  } */
}
