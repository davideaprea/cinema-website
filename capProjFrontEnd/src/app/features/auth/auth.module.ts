import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { InpPasswordComponent } from './components/inp-password/inp-password.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AuthComponent } from './auth.component';
import { PasswordModule } from 'primeng/password';
import { DividerModule } from 'primeng/divider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { CheckboxModule } from 'primeng/checkbox';

@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent,
    InpPasswordComponent,
    AuthComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    SharedModule,
    PasswordModule,
    DividerModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    ToastModule,
    CheckboxModule
  ]
})
export class AuthModule { }
