import { Hall } from "./hall";
import { Movie } from "./movie";

export interface Schedule {
  readonly id?:number,
  movie:Movie,
  hall:Hall,
  startTime:Date
}
