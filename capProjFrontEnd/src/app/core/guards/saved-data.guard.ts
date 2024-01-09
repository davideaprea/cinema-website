import { CanDeactivateFn } from '@angular/router';
import { IFormComponent } from '../models/i-form-component';

export const savedDataGuard: CanDeactivateFn<IFormComponent> =  (component, currentRoute, currentState, nextState) => {
  if(component.isFormDirty()) return component.openConfirmationDialog();
  return true;
};
