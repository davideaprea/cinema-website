import { Schedule } from "../../admin/models/schedule";

export interface BookingBody {
  readonly schedule:Schedule,
  readonly seat:{nseat:number, nrow:number}
}
