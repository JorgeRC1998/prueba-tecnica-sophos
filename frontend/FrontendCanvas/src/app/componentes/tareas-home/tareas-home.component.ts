import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  // formulario: FormGroup;
  datosToken: any;
  estadosTareas = [
    'sin asignar',
    'pendiente',
    'ejecutando',
    'finalizado'
  ];
  tareasList = [];
  usuariosList: any = [];
  displayedColumns: string[] = ['id', 'titulo', 'descripcion', 'estado', 'idUsuario', 'opciones'];
  dataRow: any = {};

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
    // this.formulario = this.construirFormulario();
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

  async consultarDatosUsr() {
    let datosUsuario: any;
    try {
      let consultaUser = {
        nombre: this.datosToken.sub,
        tipoUsuario: ''
      };
      let responseUsr = await this.usuariosService.consultarUsuario(consultaUser);
      if (responseUsr) {
        if (responseUsr.codigo == '1') {
          datosUsuario = responseUsr.usuarios[0];
        } else {
          this.notificationService.showWarning(responseUsr.descripcion, 'Alerta');
        }
      }
    } catch (err: any) {
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
    return datosUsuario;
  }

  async consultarTareas() {
    try {
      let datosUsr = await this.consultarDatosUsr();
      let usuario = datosUsr;
      let responseTar: any;
      let consultaTarea = {
        idUsuario: usuario.id,
        estado: this.estado?.value,
      };

      if (consultaTarea.estado == "sin asignar") {
        responseTar = await this.tareasService.consultarTareasSinAsignacion();
      } else {
        responseTar = await this.tareasService.consultarTareas(consultaTarea);
      }

      if (responseTar) {
        if (responseTar.codigo == '1') {
          this.tareasList = responseTar.tareas;
          this.notificationService.showSuccess('Consulta exitosa', 'Exito');
        } else {
          this.notificationService.showWarning(responseTar.descripcion, 'Alerta');
        }
      }
    } catch (err: any) {
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

  verTarea(tarea: any) {
    this.dataRow = tarea;
    this.callVerTarea(this.dataRow);
  }

  async callVerTarea(tarea: any = null) {
    const dialogRef = this.matDialog.open(TareasRegistroComponent, {
      width: '500px',
      height: '400px',
      data: {
        row: tarea,
        esConsulta: true
      }
    });

    dialogRef.afterClosed().subscribe(async result => {
      if (result.resultado) {
        await this.consultarTareas();
      }
    });
  }

  modificarTarea(tarea: any) {
    this.dataRow = tarea;
    this.callMoficiarRegistrarTarea(this.dataRow);
  }

  async callMoficiarRegistrarTarea(dataRow: any = null){
    const dialogRef = this.matDialog.open(TareasRegistroComponent, {
      width: '500px',
      height: '400px',
      data: {
        row: dataRow
      }
    });

    dialogRef.afterClosed().subscribe(async result => {
      if (result.resultado) {
        await this.consultarTareas();
      }
    });
  }

  eliminarTarea(tarea: any) {
    this.dataRow = tarea;
    this.callEliminarTarea();
  }

  async callEliminarTarea(){
    try {
      let responseLib: any;

      responseLib = await this.tareasService.eliminarTarea(this.dataRow.id);

      if (responseLib) {
        if (responseLib.codigo == '1') {
          this.notificationService.showSuccess(responseLib.descripcion, 'Exito');
          this.consultarTareas();
        } else {
          this.notificationService.showWarning(responseLib.descripcion, 'Alerta');
        }
      }
    } catch (err: any) {
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

  asignarTarea(tarea: any) {
    this.dataRow = tarea;
    this.callAsigarTarea();
  }

  async callAsigarTarea(){
    try {
      let datosUsr = await this.consultarDatosUsr();
      let responseAsign: any;
      let asignacionTarea = {
        id: this.dataRow.id,
        idUsuario: datosUsr.id
      }

      responseAsign = await this.tareasService.asignarTarea(asignacionTarea);

      if (responseAsign) {
        if (responseAsign.codigo == '1') {
          this.notificationService.showSuccess(responseAsign.descripcion, 'Exito');
          this.estado?.setValue('pendiente');
          this.consultarTareas();
        } else {
          this.notificationService.showWarning(responseAsign.descripcion, 'Alerta');
        }
      }
    } catch (err: any) {
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

  liberarTarea(tarea: any) {
    this.dataRow = tarea;
    this.callLiberarTarea();
  }

  async callLiberarTarea(){
    try {
      let responseLib: any;
      let tarea = {
        idTarea: this.dataRow.id
      };

      responseLib = await this.tareasService.liberarTarea(tarea);

      if (responseLib) {
        if (responseLib.codigo == '1') {
          this.notificationService.showSuccess(responseLib.descripcion, 'Exito');
          this.consultarTareas();
        } else {
          this.notificationService.showWarning(responseLib.descripcion, 'Alerta');
        }
      }
    } catch (err: any) {
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

}
