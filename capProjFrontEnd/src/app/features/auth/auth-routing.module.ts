import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthComponent } from './auth.component';
import { AccountVerificationComponent } from './pages/account-verification/account-verification.component';

const routes: Routes = [
  {path: "", component: AuthComponent, children: [
    {path: "register", component: RegisterComponent},
    {path: "login", component: LoginComponent},
    {path: "account-verification", component: AccountVerificationComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
