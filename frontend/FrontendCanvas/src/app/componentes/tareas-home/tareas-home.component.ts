import { Component, OnDestroy, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { TareasService } from 'src/app/servicios/tareas.service'; 
import { DecodeService } from 'src/app/servicios/decodertoken.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { MatDialog } from '@angular/material/dialog';
import { TareasRegistroComponent } from '../tareas-registro/tareas-registro.component';

export interface TareaElement {
  id: number;
  titulo: string;
  descripcion: string;
  estado: string;
  usuario: String,
  idUsuario: number
}
@Component({
  selector: 'app-tareas-home',
  templateUrl: './tareas-home.component.html',
  styleUrls: ['./tareas-home.component.css']
})
export class TareasHomeComponent implements OnInit {

  formularioHomeTareas: FormGroup;
  datosToken: any;
  estadosTareas = [
    'sin asignar',
    'pendiente',
    'ejecutando',
    'finalizado'
  ];
  tareasList = [];
  displayedColumns: string[] = ['id', 'titulo', 'descripcion', 'estado', 'idUsuario', 'opciones'];

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private tareasService: TareasService,
    private decodeService: DecodeService,
    private usuariosService: UsuariosService,
    private matDialog: MatDialog,
  ) {
    this.formularioHomeTareas = this.construirFormulario();
  }

  public ngOnInit(): void {
    this.datosToken = this.decodeService.decodeToken();
    this.consultarTareas();
    this.estado?.setValue(this.estadosTareas[0]);
  }

  public ngOnDestroy(): void {
    
  }

  construirFormulario(): FormGroup {
    return this.formBuilder.group({
      estado: new FormControl(null, [Validators.required, Validators.maxLength(24)])
    });
  }

  get estado() { return this.formularioHomeTareas.get('estado'); }

  async consultarDatosUsr(){
    let datosUsuario: any;
    try{
      let consultaUser = {
        nombre: this.datosToken.sub,
        tipoUsuario: ''
      };
      let responseUsr = await this.usuariosService.consultarUsuario(consultaUser);
      if(responseUsr){
        if(responseUsr.codigo == '1'){
          datosUsuario = responseUsr.usuarios[0];
        }else{
          this.notificationService.showWarning(responseUsr.descripcion, 'Alerta');
        }
      }
    }catch(err: any){
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
    return datosUsuario;
  }

  async consultarTareas(){
    try{
      let datosUsr = await this.consultarDatosUsr();
      let usuario = datosUsr;
      let responseTar: any;
      let consultaTarea = {
        idUsuario: usuario.id,
        estado: this.estado?.value,
      };
      
      if(consultaTarea.estado == "sin asignar"){
        responseTar = await this.tareasService.consultarTareasSinAsignacion();
      }else{
        responseTar = await this.tareasService.consultarTareas(consultaTarea);
      }
      
      if(responseTar){
        if(responseTar.codigo == '1'){
          this.tareasList = responseTar.tareas;
          this.notificationService.showSuccess('Consulta exitosa', 'Exito');
        }else{
          this.notificationService.showWarning(responseTar.descripcion, 'Alerta');
        }
      }
    }catch(err: any){
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

  async verTarea(tarea: any){
    alert('detalle');
  }

  async modificarTarea(tarea: any){
    alert('modificaion');
  }

  async eliminarTarea(tarea: any){
    alert('eliminacion');
  }

  async asignarTarea(tarea: any){
    console.log('hi');
    try{
      let datosUsr = await this.consultarDatosUsr();
      let responseAsign: any;
      let asignacionTarea = {
        id: '', 
        idUsuario: datosUsr.id
      }

      responseAsign = await this.tareasService.asignarTarea(asignacionTarea);
    }catch(err: any){
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

  async liberarTarea(tarea: any){
    alert('liberacion');
  }

  async desplegarRegistroTarea(){
    const dialogRef = this.matDialog.open(TareasRegistroComponent, {
      width: '500px',
      height: '400px',
      data: { 
        // data: {
        //   roles: this.rolesList,
        //   usuario: usuario
        // }
      }
    });

    dialogRef.afterClosed().subscribe(async result => {
      if(result.resultado){
        await this.consultarTareas();
      }
    });
  }

}
