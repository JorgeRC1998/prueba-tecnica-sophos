import { Component, OnDestroy, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { TareasService } from 'src/app/servicios/tareas.service'; 
import { DecodeService } from 'src/app/servicios/decodertoken.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { MatDialog } from '@angular/material/dialog';
import { UsuariosRegistroComponent } from '../usuarios-registro/usuarios-registro.component';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  formularioUsuarios: FormGroup;
  datosToken: any;
  tiposUsuarios = [
    'administrador',
    'miembro',
    'invitado'
  ];
  usuariosList = [];
  displayedColumns: string[] = ['id', 'nombre', 'identificacion', 'tipoUsuario', 'usuario', 'password', 'opciones'];
  dataRow: any = {};
  objUsuario: any = {};
  esConsulta: any = false;
  esGuardadoNuevo = 1;

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private decodeService: DecodeService,
    private usuariosService: UsuariosService,
    private matDialog: MatDialog,
  ) {
    this.formularioUsuarios = this.construirFormulario();
   }

  ngOnInit(): void {
    this.datosToken = this.decodeService.decodeToken();
  }

  construirFormulario(): FormGroup {
    return this.formBuilder.group({
      nombre: new FormControl(null, [Validators.maxLength(24)]),
      tipoUsuario: new FormControl(null, [Validators.maxLength(24)])
    });
  }

  get nombre() { return this.formularioUsuarios.get('nombre'); }
  get tipoUsuario() { return this.formularioUsuarios.get('tipoUsuario'); }

  async consultarUsuarios(){
    try{
      let responseUsr: any;
      let consultaUsuario = {
        nombre: this.nombre?.value,
        tipoUsuario: this.tipoUsuario?.value,
      };
      consultaUsuario.nombre = consultaUsuario.nombre == null ? '' : consultaUsuario.nombre;
      consultaUsuario.tipoUsuario = consultaUsuario.tipoUsuario == null ? '' : consultaUsuario.tipoUsuario;
    
      responseUsr = await this.usuariosService.consultarUsuario(consultaUsuario);
      
      if(responseUsr){
        if(responseUsr.codigo == '1'){
          this.usuariosList = responseUsr.usuarios;
          this.notificationService.showSuccess('Consulta exitosa', 'Exito');
        }else{
          this.notificationService.showWarning(responseUsr.descripcion, 'Alerta');
        }
      }
    }catch(err: any){
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

  modificarUsuario(usuario: any) {
    this.dataRow = usuario;
    this.callMoficiarRegistrarUsuario(this.dataRow);
  }

  async callMoficiarRegistrarUsuario(dataRow: any = null){
    const dialogRef = this.matDialog.open(UsuariosRegistroComponent, {
      width: '500px',
      height: '525px',
      data: {
        row: dataRow
      }
    });

    dialogRef.afterClosed().subscribe(async result => {
      if (result.resultado) {
        await this.consultarUsuarios();
      }
    });
  }

  verUsuario(usuario: any) {
    this.dataRow = usuario;
    this.callVerUsuario(this.dataRow);
  }

  async callVerUsuario(usuario: any = null) {
    const dialogRef = this.matDialog.open(UsuariosRegistroComponent, { 
      width: '500px',
      height: '525px',
      data: {
        row: usuario,
        esConsulta: true
      }
    });

    dialogRef.afterClosed().subscribe(async result => {
      if (result.resultado) {
        await this.consultarUsuarios();
      }
    });
  }

  eliminarUsuario(usuario: any) {
    this.dataRow = usuario;
    this.callEliminarUsuario();
  }

  async callEliminarUsuario(){
    try {
      let responseLib: any;

      responseLib = await this.usuariosService.eliminarUsuario(this.dataRow.id);

      if (responseLib) {
        if (responseLib.codigo == '1') {
          this.notificationService.showSuccess(responseLib.descripcion, 'Exito');
          this.consultarUsuarios();
        } else {
          this.notificationService.showWarning(responseLib.descripcion, 'Alerta');
        }
      }
    } catch (err: any) {
      this.notificationService.showError(err.error.descripcion, 'Error');
    }
  }

}
