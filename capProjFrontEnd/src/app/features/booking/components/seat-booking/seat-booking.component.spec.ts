import { TestBed } from "@angular/core/testing";
import { SeatBookingComponent } from "./seat-booking.component";
import { SeatStatus } from "./seat-status";
import { HttpClientModule } from "@angular/common/http";
import { BookingService } from "../../services/booking.service";
import { of } from "rxjs";
import { Booking } from "../../models/booking";
import { Schedule } from "src/app/features/admin/models/schedule";
import { Movie } from "src/app/features/admin/models/movie";
import { Hall } from "src/app/features/admin/models/hall";
import { HallStatus } from "src/app/features/admin/models/hall-status";
import { Receipt } from "../../models/receipt";
import { IUser } from "src/app/core/models/iuser";

let component: SeatBookingComponent;
let bookingService;

describe("SeatBookingComponent", () => {
  beforeEach(() => {
    let movie:Movie={
      id: 0,
      title: "",
      cover: "",
      backgroundCover: "",
      releaseDate: new Date(),
      duration: 0,
      director: "",
      actors: "",
      description: "",
      genres: [],
      isTridimensional: false
    }

    let hall:Hall={
      id: 0,
      nrows: 0,
      nseatsPerRow: 0,
      status: HallStatus.AVAILABLE
    }

    let schedule:Schedule={
      id: 0,
      movie: movie,
      hall: hall,
      startTime: new Date()
    }

    let user:IUser={
      accessToken: "",
      tokenType: "",
      username: ""
    }

    let receipt:Receipt={
      id: 0,
      user: user,
      bookings: [],
      purchaseTime: new Date(),
      totPrice: 0
    }

    let scheduleBookings:Booking={
      id: 0,
      viewSchedule: schedule,
      seat: {
        nseat: 0,
        nrow: 0
      },
      receipt: receipt
    }

    bookingService = jasmine.createSpyObj('BookingService', ['getScheduleBookings', 'getSchedule']);
    bookingService.getScheduleBookings.and.returnValue(of(scheduleBookings));

    TestBed.configureTestingModule({
      declarations: [SeatBookingComponent],
      imports: [HttpClientModule],
      providers: [
        { provide: BookingService, useValue: bookingService }
      ]
    });

    component = TestBed.createComponent(SeatBookingComponent).componentInstance;
  });

  it("should select a seat", () => {
    let row = 2, seat = 3, seats = component.seats;

    component.manageSeats(row, seat);

    const selectedSeat = seats.map(row => row.filter(seat => seat == SeatStatus.SELECTED));
    const otherSeats = seats.map(row => row.filter(seat => seat != SeatStatus.AVAILABLE && seat != SeatStatus.SELECTED));

    expect(selectedSeat.length).toBe(1);
    expect(selectedSeat[row][seat]).toBe(SeatStatus.SELECTED);
    expect(selectedSeat[row][seat - 1] && selectedSeat[row][seat + 1]).toBe(SeatStatus.AVAILABLE);

    for (let row of otherSeats) {
      for (let seat of row) {
        expect(seat).toBe(SeatStatus.NOT_AVAILABLE);
      }
    }
  });
});
