import { Component, OnDestroy, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { MatDialogRef } from '@angular/material/dialog';
import { TareasHomeComponent } from '../tareas-home/tareas-home.component';
import { TareasService } from 'src/app/servicios/tareas.service';

@Component({
  selector: 'app-tareas-registro',
  templateUrl: './tareas-registro.component.html',
  styleUrls: ['./tareas-registro.component.css']
})
export class TareasRegistroComponent implements OnInit {

  formularioRegTar: FormGroup;

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private tareasService: TareasService,
    private dialogRef: MatDialogRef<TareasHomeComponent>,
  ) { 
    this.formularioRegTar = this.construirFormulario();
  }

  ngOnInit(): void {

  }

  construirFormulario(): FormGroup {
    return this.formBuilder.group({
      titulo: new FormControl(null, [Validators.required, Validators.maxLength(49)]),
      descripcion: new FormControl(null, [Validators.required, Validators.maxLength(499)]),
    });
  }

  get titulo() { return this.formularioRegTar.get('titulo'); }
  get descripcion() { return this.formularioRegTar.get('descripcion'); }

  async registrarTarea(){
    if(this.formularioRegTar.valid){
      try{
        let usuario = {
          titulo: this.titulo?.value,
          descripcion: this.descripcion?.value
        };
        let response = await this.tareasService.agregarTarea(usuario);
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

}
