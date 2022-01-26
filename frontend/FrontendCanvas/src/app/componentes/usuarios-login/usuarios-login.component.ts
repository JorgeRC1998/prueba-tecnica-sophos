import { Component, OnDestroy, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';

@Component({
  selector: 'app-usuarios-login',
  templateUrl: './usuarios-login.component.html',
  styleUrls: ['./usuarios-login.component.css']
})
export class UsuariosLoginComponent implements OnInit, OnDestroy {

  formularioLogin: FormGroup;

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private usuariosService: UsuariosService
  ) {
    this.formularioLogin = this.construirFormulario();
  }

  public ngOnInit(): void {

  }

  public ngOnDestroy(): void {
    
  }

  construirFormulario(): FormGroup {
    return this.formBuilder.group({
      usuario: new FormControl(null, [Validators.required, Validators.maxLength(24)]),
      password: new FormControl(null, [Validators.required, Validators.maxLength(24)]),
    });
  }

  get usuario() { return this.formularioLogin.get('usuario'); }
  get password() { return this.formularioLogin.get('password'); }

  async login(){
    if(this.formularioLogin.valid){
      try{
        let login = {
          usuario: this.usuario?.value,
          password: this.password?.value
        };
        let response = await this.usuariosService.login(login);
        if(response){
          if(response.codigo == '1'){
            sessionStorage.setItem('access_token', response.token);
            this._router.navigateByUrl('/tareas-home');
          }else{
            this.notificationService.showWarning(response.descripcion, 'Alerta');
          }
        }
      }catch(err: any){
        this.notificationService.showError(err.error.descripcion, 'Error Interno');
      }
    }else{
      this.notificationService.showWarning('Por favor revise la totalidad y validez de los datos de este formulario', 'Datos Incompletos');
    }
  }

}
