import { IUser } from "src/app/core/models/iuser";
import { BookingBody } from "./booking-body";

export interface ReceiptBody {
  user:IUser,
  bookings:BookingBody[],
}
