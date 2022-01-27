import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class TareasService {

    constructor(private httpClient: HttpClient) {

    }

    async consultarTareas(filtro: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/tareas/consulta-tareas`;
        return await this.httpClient.post(url, filtro, {headers}).toPromise();
    }

    async consultarTareasSinAsignacion(): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/tareas/consulta-tareas-noasignadas`;
        return await this.httpClient.get(url, {headers}).toPromise();
    }

    async agregarTarea(filtro: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/tareas/insercion-tarea`;
        return await this.httpClient.post(url, filtro, {headers}).toPromise();
    }

    async asignarTarea(filtro: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/tareas/asignacion-tarea`;
        return await this.httpClient.put(url, filtro, {headers}).toPromise();
    }

    async liberarTarea(idTarea: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/tareas/liberacion-tarea/${idTarea}`;
        return await this.httpClient.put(url, {headers}).toPromise();
    }

    async eliminarTarea(idTarea: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/tareas/eliminado-tarea/${idTarea}`;
        return await this.httpClient.delete(url, {headers}).toPromise();
    }

}