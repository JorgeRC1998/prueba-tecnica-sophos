import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TareasHomeComponent } from './componentes/tareas-home/tareas-home.component';
import { TareasRegistroComponent } from './componentes/tareas-registro/tareas-registro.component';
import { UsuariosLoginComponent } from './componentes/usuarios-login/usuarios-login.component';
import { UsuariosRegistroComponent } from './componentes/usuarios-registro/usuarios-registro.component';
import { UsuariosComponent } from './componentes/usuarios/usuarios.component';

const routes: Routes = [
  {
    path: 'usuarios-registro',
    component: UsuariosRegistroComponent
  },
  {
    path: 'usuarios',
    component: UsuariosComponent
  },
  {
    path: 'tareas-registro',
    component: TareasRegistroComponent
  },
  {
    path: 'tareas',
    component: TareasHomeComponent
  },
  {
    path: 'usuarios-login',
    component: UsuariosLoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
