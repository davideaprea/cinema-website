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

@NgModule({
  declarations: [
    AdminComponent,
    MovieFormComponent,
    ScheduleFormComponent,
    OurmoviesComponent,
    SchedulesComponent,
    SidebarComponent
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
    ConfirmDialogModule
  ]
})
export class AdminModule { }
