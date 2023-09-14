import { Schedule } from "../../admin/models/schedule";
import { Receipt } from "./receipt";

export interface Booking {
  readonly id:number,
  readonly schedule:Schedule,
  readonly seat:{nseat:number, nrow:number},
  readonly receipt:Receipt
}
