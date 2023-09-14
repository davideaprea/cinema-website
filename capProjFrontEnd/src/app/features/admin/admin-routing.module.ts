import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { MovieFormComponent } from './pages/movie-form/movie-form.component';
import { ScheduleFormComponent } from './pages/schedule-form/schedule-form.component';
import { OurmoviesComponent } from './pages/ourmovies/ourmovies.component';

const routes: Routes = [{
  path: '', component: AdminComponent, children: [
    { path: "addmovie", component: MovieFormComponent },
    { path: "addschedule", component: ScheduleFormComponent },
    { path: "ourmovies", component: OurmoviesComponent },
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }