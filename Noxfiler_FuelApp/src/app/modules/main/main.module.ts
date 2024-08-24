import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainRoutingModule } from './main-routing.module';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { LayoutComponent } from './components/layout/layout.component';
import { HeaderComponent } from './components/header/header.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatToolbarModule } from '@angular/material/toolbar';

@NgModule({
  declarations: [
    SidenavComponent,
    LayoutComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    MainRoutingModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatButtonModule,
    FormsModule,
    MatMenuModule,
    MatTooltipModule,
    MatToolbarModule,
  ]
})
export class MainModule { }
