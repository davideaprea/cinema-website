import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, ValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  f!: FormGroup;
  registrationCompleted: boolean = false;
  emailError?: string;
  usernameError?: string;
  private takenCredentials: string[] = [];
  private readonly takenCredentialsValidator = (control: AbstractControl): ValidationErrors | null => this.takenCredentials.includes(control.value) ? { credentialTaken: true } : null;

  constructor(private authService: AuthService) {
    this.f = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email, this.takenCredentialsValidator]),
      password: new FormControl("", [Validators.required, Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$")]),
      username: new FormControl("", [Validators.required, this.takenCredentialsValidator]),
      name: new FormControl("")
    });
  }

  public get emailControl(){
    return this.f.get("email")!;
  }

  public get usernameControl(){
    return this.f.get("username")!;
  }

  submit() {
    this.authService.register(this.f.value).subscribe({
      next: res => this.registrationCompleted = true,
      error: e => {
        const error = e.toLowerCase();
        if (error.includes("email")) {
          this.emailError = e;
          this.emailControl.setErrors({ credentialTaken: true });
          this.takenCredentials.push(this.emailControl.value);
        }
        else if (error.includes("username")) {
          this.usernameError = e;
          this.usernameControl.setErrors({ credentialTaken: true });
          this.takenCredentials.push(this.usernameControl.value);
        }
      }
    });
  }
}
