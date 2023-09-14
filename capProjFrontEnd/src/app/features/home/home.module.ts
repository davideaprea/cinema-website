import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { CoreModule } from 'src/app/core/core.module';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import { SchedulesComponent } from './pages/schedules/schedules.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { TabMenuModule } from 'primeng/tabmenu';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [
    HomeComponent,
    MovieDetailsComponent,
    SchedulesComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    CoreModule,
    SharedModule,
    TabMenuModule,
    ButtonModule
  ]
})
export class HomeModule { }
