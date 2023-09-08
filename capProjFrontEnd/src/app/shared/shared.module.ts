import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrimarybuttonComponent } from './components/primarybutton/primarybutton.component';

import { ButtonModule } from 'primeng/button';
import { InpTextComponent } from './components/inp-text/inp-text.component';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InpCheckBoxComponent } from './components/inp-check-box/inp-check-box.component';

@NgModule({
  declarations: [
    PrimarybuttonComponent,
    InpTextComponent,
    InpCheckBoxComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    InputTextModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    InpTextComponent,
    InpCheckBoxComponent
  ]
})
export class SharedModule { }