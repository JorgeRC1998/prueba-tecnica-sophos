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
    // this.consultarUsuarios(); 
    // this.tipoUsuario?.setValue(this.tiposUsuarios[1]);
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

  async desplegarRegistroUsuarios(usuario: any = null){
    const dialogRef = this.matDialog.open(UsuariosRegistroComponent, {
      width: '500px',
      height: '525px',
      data: { 
        // data: {
        //   roles: this.rolesList,
        //   usuario: usuario
        // }
      }
    });

    dialogRef.afterClosed().subscribe(async result => {
      if(result.resultado){
        await this.consultarUsuarios();
      }
    });
  }

  async verUsuario(usuario: any){
    console.log('hi');
  }

  async modificarUsuario(usuario: any){

  }

  async eliminarUsuario(usuario: any){

  }

}
