import { Component, OnDestroy, OnInit, Optional, Inject } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UsuariosComponent } from '../usuarios/usuarios.component';

@Component({
  selector: 'app-usuarios-registro',
  templateUrl: './usuarios-registro.component.html',
  styleUrls: ['./usuarios-registro.component.css']
})
export class UsuariosRegistroComponent implements OnInit {

  formularioRegUsr: FormGroup;
  tiposUsuarios = [
    'administrador',
    'miembro',
    'invitado'
  ];
  objUsuario: any = {};
  esConsulta: any = false;
  esGuardadoNuevo = 1;

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private usuariosService: UsuariosService,
    private dialogRef: MatDialogRef<UsuariosComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public usuarioRow: any,
  ) { 
    this.formularioRegUsr = this.construirFormulario();
    this.objUsuario = usuarioRow.row;
    this.esConsulta = usuarioRow.esConsulta;
  }

  ngOnInit(): void {
    if(this.objUsuario != null){
      this.llenarFormulario(this.objUsuario);
    }
  }

  construirFormulario(): FormGroup {
    return this.formBuilder.group({
      nombre: new FormControl(null, [Validators.required, Validators.maxLength(24)]),
      identificacion: new FormControl(null, [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(14)]),
      tipoUsuario: new FormControl(null, [Validators.required]),
      usuario: new FormControl(null, [Validators.required, Validators.maxLength(24)]),
      password: new FormControl(null, [Validators.required, Validators.maxLength(24)])
    });
  }

  get nombre() { return this.formularioRegUsr.get('nombre'); }
  get identificacion() { return this.formularioRegUsr.get('identificacion'); }
  get tipoUsuario() { return this.formularioRegUsr.get('tipoUsuario'); }
  get usuario() { return this.formularioRegUsr.get('usuario'); }
  get password() { return this.formularioRegUsr.get('password'); }


  limpiarCampos(){
    this.formularioRegUsr.reset();
  }

  async registrarUsuario(){
    if(this.formularioRegUsr.valid){
      try{
        let usuario: any = {};
        let response: any = {};
        
        if(this.esGuardadoNuevo == 1){
          usuario = {
            nombre: this.nombre?.value,
            identificacion: this.identificacion?.value,
            tipoUsuario: this.tipoUsuario?.value,
            usuario: this.usuario?.value,
            password: this.password?.value
          };
        }else{
          usuario = {
            id: this.objUsuario.id,
            nombre: this.nombre?.value,
            identificacion: this.identificacion?.value,
            tipoUsuario: this.tipoUsuario?.value,
            usuario: this.usuario?.value,
            password: this.password?.value
          };
        }

        if(this.esGuardadoNuevo == 1){
          response = await this.usuariosService.registrarUsuario(usuario);
        }else{
          response = await this.usuariosService.actualizarUsuario(usuario);
        }

        if(response){
          if(response.codigo == '1'){
            this.cancelar(true);
            this.notificationService.showSuccess(response.descripcion, response.mensaje);
          }else{
            this.notificationService.showWarning(response.descripcion, 'Alerta');
          }
        }
      }catch(err: any){
        this.notificationService.showError(err.error.descripcion, 'Error');
      }
    }else{
      this.notificationService.showWarning('Por favor revise la totalidad y validez de los datos de este formulario', 'Datos Incompletos');
    }
  }

  cancelar(param: any = null){
    this.dialogRef.close({resultado: param}); 
  }

  llenarFormulario(usuario: any){
    if(usuario !== null){
      this.nombre?.setValue(usuario.nombre);
      this.identificacion?.setValue(usuario.identificacion);
      this.tipoUsuario?.setValue(usuario.tipoUsuario);
      this.usuario?.setValue(usuario.usuario);
      this.password?.setValue(usuario.password);
      this.esGuardadoNuevo = 0;
    }else{
      this.esGuardadoNuevo = 1;
    }
  }

}
