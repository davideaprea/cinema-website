import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { MovieFormComponent } from './pages/movie-form/movie-form.component';
import { ScheduleFormComponent } from './components/schedule-form/schedule-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { ButtonModule } from 'primeng/button';
import { FileUploadModule } from 'primeng/fileupload';
import { CalendarModule } from 'primeng/calendar';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { InputNumberModule } from 'primeng/inputnumber';
import { MultiSelectModule } from 'primeng/multiselect';
import { DropdownModule } from 'primeng/dropdown';
import { OurmoviesComponent } from './pages/ourmovies/ourmovies.component';
import { SchedulesComponent } from './pages/schedules/schedules.component';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HallsComponent } from './pages/halls/halls.component';
import { HallFormComponent } from './components/hall-form/hall-form.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ChartModule } from 'primeng/chart';
import { BarChartComponent } from './components/bar-chart/bar-chart.component';

@NgModule({
  declarations: [
    AdminComponent,
    MovieFormComponent,
    ScheduleFormComponent,
    OurmoviesComponent,
    SchedulesComponent,
    SidebarComponent,
    HallsComponent,
    HallFormComponent,
    DashboardComponent,
    BarChartComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    ButtonModule,
    FileUploadModule,
    CalendarModule,
    InputTextareaModule,
    InputNumberModule,
    MultiSelectModule,
    DropdownModule,
    TableModule,
    InputTextModule,
    ToastModule,
    ConfirmDialogModule,
    ChartModule
  ]
})
export class AdminModule { }
