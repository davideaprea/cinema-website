import { HallStatus } from "./hall-status";

export interface Hall {
  readonly id?:number,
  nRows:number,
  nSeatsPerRow:number,
  status:HallStatus
}
