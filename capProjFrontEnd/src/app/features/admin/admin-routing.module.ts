import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { MovieFormComponent } from './pages/movie-form/movie-form.component';
import { OurmoviesComponent } from './pages/ourmovies/ourmovies.component';
import { SchedulesComponent } from './pages/schedules/schedules.component';
import { HallsComponent } from './pages/halls/halls.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { savedDataGuard } from 'src/app/core/guards/saved-data.guard';

const routes: Routes = [{
  path: '', component: AdminComponent, children: [
    { path: "addmovie", component: MovieFormComponent, canDeactivate: [savedDataGuard] },
    { path: "ourmovies", component: OurmoviesComponent },
    { path: "schedules", component: SchedulesComponent },
    { path: "halls", component: HallsComponent },
    { path: "dashboard", component: DashboardComponent },
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
