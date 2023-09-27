import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile.component';
import { MyticketsComponent } from './pages/mytickets/mytickets.component';

const routes: Routes = [{
  path: '', component: ProfileComponent, children: [
    { path: "mytickets", component: MyticketsComponent }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
