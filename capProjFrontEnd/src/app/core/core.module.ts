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

@NgModule({
  declarations: [
    NavbarComponent,
    DropMenuComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    RouterModule,
    MenuModule,
    InputTextModule
  ],
  exports: [
    NavbarComponent,
    FooterComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ]
})
export class CoreModule { }
