import { Genres } from "./genres"

export interface Movie {
  readonly id:number,
  title:string,
  readonly cover:{id:number, name:string, path:string},
  trailerLink:string,
  releaseDate:Date,
  duration:number,
  director:string,
  actors:string,
  description:string,
  genres:Genres[],
  isTridimensional:boolean
}
