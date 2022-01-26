import { Component, OnDestroy, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';

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

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private usuariosService: UsuariosService
  ) { 
    this.formularioRegUsr = this.construirFormulario();
  }

  ngOnInit(): void {

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

  }

}
