import { Genres } from "src/app/features/admin/models/genres";

export interface MovieBody {
  title:string,
  cover:File,
  backgroundCover:File,
  releaseDate:Date,
  duration:number,
  director:string,
  actors:string,
  description:string,
  genres:Genres[],
  isTridimensional:boolean
}
