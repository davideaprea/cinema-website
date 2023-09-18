export interface BookingBody {
  readonly scheduleId:number,
  readonly seat:{nseat:number, nrow:number}
}
