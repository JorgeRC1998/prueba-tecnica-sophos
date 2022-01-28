import { Component, OnDestroy, OnInit, Optional, Inject } from '@angular/core';
import {  Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators, Form } from '@angular/forms';
import { NotificationService } from 'src/app/modulos/notificacion/notificacion.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TareasHomeComponent } from '../tareas-home/tareas-home.component';
import { TareasService } from 'src/app/servicios/tareas.service';

@Component({
  selector: 'app-tareas-registro',
  templateUrl: './tareas-registro.component.html',
  styleUrls: ['./tareas-registro.component.css']
})
export class TareasRegistroComponent implements OnInit {

  formularioRegTar: FormGroup;
  objTarea: any = {};
  esConsulta: any = false;
  esGuardadoNuevo = 1;

  estadosTareas = [
    'pendiente',
    'ejecutando',
    'finalizado'
  ];

  constructor(
    private _router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    private tareasService: TareasService,
    private dialogRef: MatDialogRef<TareasHomeComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public tarea: any
  ) { 
    this.formularioRegTar = this.construirFormulario();
    this.objTarea = tarea.row;
    this.esConsulta = tarea.esConsulta;
  }

  ngOnInit(): void {
    if(this.objTarea != null){
      this.llenarFormulario(this.objTarea);
    }
    
  }

  construirFormulario(): FormGroup {
    return this.formBuilder.group({
      titulo: new FormControl(null, [Validators.required, Validators.maxLength(49)]),
      descripcion: new FormControl(null, [Validators.required, Validators.maxLength(499)]),
      estado: new FormControl(null, [Validators.maxLength(14)]),
    });
  }

  get titulo() { return this.formularioRegTar.get('titulo'); }
  get descripcion() { return this.formularioRegTar.get('descripcion'); }
  get estado() { return this.formularioRegTar.get('estado'); }

  async registrarTarea(){
    if(this.formularioRegTar.valid){
      try{
        let tarea: any = {};
        let response: any = {};

        if(this.esGuardadoNuevo == 1){
          tarea = {
            titulo: this.titulo?.value,
            descripcion: this.descripcion?.value
          };
        }else{
          tarea = {
            id: this.objTarea.id,
            titulo: this.titulo?.value,
            descripcion: this.descripcion?.value,
            estado: this.estado?.value
          };
        }

        if(this.esGuardadoNuevo == 1){
          response = await this.tareasService.agregarTarea(tarea);
        }else{
          response = await this.tareasService.actualizarTarea(tarea);
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

  llenarFormulario(tarea: any){
    if(tarea !== null){
      this.titulo?.setValue(tarea.titulo);
      this.descripcion?.setValue(tarea.descripcion);
      this.estado?.setValue(tarea.estado);
      this.esGuardadoNuevo = 0;
    }else{
      this.esGuardadoNuevo = 1;
    }
  }

}
