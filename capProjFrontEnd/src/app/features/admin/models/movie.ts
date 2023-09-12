import { Genres } from "src/app/features/admin/models/genres";

export interface Movie {
  readonly id?:number,
  title:string,
  cover:File
  trailerLink:string,
  releaseDate:Date,
  duration:number,
  director:string,
  actors:string,
  description:string,
  genres:Genres[],
  isTridimensional:boolean
}
