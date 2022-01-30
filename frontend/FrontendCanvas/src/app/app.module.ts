import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from '@angular/common/http';

import { ToastrModule } from 'ngx-toastr';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import { TareasHomeComponent } from './componentes/tareas-home/tareas-home.component';
import { TareasRegistroComponent } from './componentes/tareas-registro/tareas-registro.component';
import { UsuariosRegistroComponent } from './componentes/usuarios-registro/usuarios-registro.component';
import { UsuariosLoginComponent } from './componentes/usuarios-login/usuarios-login.component';
import { UsuariosComponent } from './componentes/usuarios/usuarios.component';

@NgModule({
  declarations: [
    AppComponent,
    TareasHomeComponent,
    TareasRegistroComponent,
    UsuariosRegistroComponent,
    UsuariosLoginComponent,
    UsuariosComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    MatCardModule,
    MatDividerModule,
    MatListModule,
    MatSelectModule,
    MatGridListModule,
    MatTableModule,
    MatIconModule,
    MatMenuModule
  ],
  providers: [UsuariosLoginComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
