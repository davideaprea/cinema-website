import { CanDeactivateFn } from '@angular/router';

export const savedBookingGuard: CanDeactivateFn<unknown> = (component, currentRoute, currentState, nextState) => {
  return true;
};
