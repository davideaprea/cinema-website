import { HallStatus } from "./hall-status";

export interface Hall {
  readonly id:number,
  nrows:number,
  nseatsPerRow:number,
  status:HallStatus
}
