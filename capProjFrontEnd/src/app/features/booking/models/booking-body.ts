import { Schedule } from "../../admin/models/schedule";

export interface BookingBody {
  readonly viewSchedule:Schedule,
  readonly seat:{nseat:number, nrow:number}
}
