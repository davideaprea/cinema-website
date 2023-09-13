import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { MovieFormComponent } from './pages/movie-form/movie-form.component';
import { ScheduleFormComponent } from './pages/schedule-form/schedule-form.component';
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

@NgModule({
  declarations: [
    AdminComponent,
    MovieFormComponent,
    ScheduleFormComponent,
    OurmoviesComponent
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
    DropdownModule
  ]
})
export class AdminModule { }
