import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './components/navbar/navbar.component';

import { ButtonModule } from 'primeng/button';
import { RouterModule } from '@angular/router';
import { MenuModule } from 'primeng/menu';
import { DropMenuComponent } from './components/drop-menu/drop-menu.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { FooterComponent } from './components/footer/footer.component';
import { InputTextModule } from 'primeng/inputtext';
import { SharedModule } from '../shared/shared.module';
import { MessageService } from 'primeng/api';
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { AvgImageColorDirective } from './directives/avg-image-background-color.directive';

@NgModule({
  declarations: [
    NavbarComponent,
    DropMenuComponent,
    FooterComponent,
    AvgImageColorDirective
  ],
  imports: [
    CommonModule,
    ButtonModule,
    RouterModule,
    MenuModule,
    InputTextModule,
    SharedModule
  ],
  exports: [
    NavbarComponent,
    FooterComponent,
    AvgImageColorDirective
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },
    MessageService
  ]
})
export class CoreModule { }
