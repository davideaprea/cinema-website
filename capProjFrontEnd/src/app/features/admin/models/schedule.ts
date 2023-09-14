import { Hall } from "./hall";
import { Movie } from "./movie";

export interface Schedule {
  readonly id:number,
  readonly movie:Movie,
  readonly hall:Hall,
  startTime:Date,
  readonly endTime:Date
}
