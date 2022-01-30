import { Component, OnDestroy, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { DataService } from 'src/app/servicios/data.service';

@Component({
  selector: 'app-usuarios-login',
  templateUrl: './usuarios-login.component.html',
  styleUrls: ['./usuarios-login.component.css']
})
export class UsuariosLoginComponent implements OnInit, OnDestroy {

  formularioLogin: FormGroup;
  message: boolean | undefined;

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private usuariosService: UsuariosService,
    private dataService: DataService,
  ) {
    this.formularioLogin = this.construirFormulario();
  }

  public ngOnInit(): void {
    this.dataService.currentMessage.subscribe(message => this.message = message);
  }

  public ngOnDestroy(): void {
    this.dataService.currentMessage.pipe();
  }

  construirFormulario(): FormGroup {
    return this.formBuilder.group({
      usuario: new FormControl(null, [Validators.required, Validators.maxLength(24)]),
      password: new FormControl(null, [Validators.required, Validators.maxLength(24)])
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
            this.dataService.changeMessage(true);
            this._router.navigateByUrl('/tareas');
            this.notificationService.showSuccess(`Bienvenido: ${login.usuario}`, 'Exito');
          }else{
            this.dataService.changeMessage(false);
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

}
