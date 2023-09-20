import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrimarybuttonComponent } from './components/primarybutton/primarybutton.component';

import { ButtonModule } from 'primeng/button';
import { InpTextComponent } from './components/inp-text/inp-text.component';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InpCheckBoxComponent } from './components/inp-check-box/inp-check-box.component';
import { GalleriaComponent } from './components/galleria/galleria.component';
import { GalleriaModule } from 'primeng/galleria';
import { CalendarModule } from 'primeng/calendar';
import { InpDateTimeComponent } from './components/inp-date-time/inp-date-time.component';
import { CardModule } from 'primeng/card';
import { CardComponent } from './components/card/card.component';
import { SharedRoutingModule } from './shared-routing.module';
import { CardsGridComponent } from './macro-components/cards-grid/cards-grid.component';

@NgModule({
  declarations: [
    PrimarybuttonComponent,
    InpTextComponent,
    InpCheckBoxComponent,
    GalleriaComponent,
    InpDateTimeComponent,
    CardComponent,
    CardsGridComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    InputTextModule,
    FormsModule,
    ReactiveFormsModule,
    GalleriaModule,
    CalendarModule,
    CardModule,
    SharedRoutingModule
  ],
  exports: [
    InpTextComponent,
    InpCheckBoxComponent,
    InpDateTimeComponent,
    CardComponent,
    CardsGridComponent,
    GalleriaComponent
  ]
})
export class SharedModule { }
