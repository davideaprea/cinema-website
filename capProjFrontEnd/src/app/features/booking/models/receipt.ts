import { IUser } from "src/app/core/models/iuser";
import { Booking } from "./booking";

export interface Receipt {
  readonly id:number,
  readonly user:IUser,
  readonly bookings:Booking[],
  readonly purchaseTime:Date;
  readonly totPrice:number;
}
