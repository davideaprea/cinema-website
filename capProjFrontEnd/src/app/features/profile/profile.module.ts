import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';
import { MyticketsComponent } from './pages/mytickets/mytickets.component';
import { CoreModule } from 'src/app/core/core.module';


@NgModule({
  declarations: [
    ProfileComponent,
    MyticketsComponent
  ],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    CoreModule
  ]
})
export class ProfileModule { }
