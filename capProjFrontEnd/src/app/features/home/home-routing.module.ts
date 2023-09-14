import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import { SchedulesComponent } from './pages/schedules/schedules.component';

const routes: Routes = [{
  path: '', component: HomeComponent, children: [
    { path: "movie-details/:id", component: MovieDetailsComponent },
    { path: "schedules", component: SchedulesComponent }
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
