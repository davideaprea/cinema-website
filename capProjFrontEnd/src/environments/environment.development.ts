const baseUrl="http://localhost:8080";

export const environment = {
  register: baseUrl+"/api/auth/register",
  verification: baseUrl+"/api/auth/verification",
  login: baseUrl+"/api/auth/login",
  movies: baseUrl+"/movies",
  schedules: baseUrl+"/schedules",
  halls: baseUrl+"/halls",
  receipts: baseUrl+"/receipts",
  bookings: baseUrl+"/bookings",
  payments: baseUrl+"/payments",
};
