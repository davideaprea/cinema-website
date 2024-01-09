export interface IFormComponent {
  isFormDirty():boolean;
  openConfirmationDialog(): Promise<boolean>;
}
