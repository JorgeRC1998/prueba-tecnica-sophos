import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class UsuariosService {

    constructor(private httpClient: HttpClient) {

    }

    async login(filtro: any): Promise<any> {
        const url = environment.urlBackendPruebaTecnica + `/auth/login`;
        return await this.httpClient.post(url, filtro).toPromise();
    }

    async registrarUsuario(filtro: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/usuarios/insercion-usuario`;
        return await this.httpClient.post(url, filtro, {headers}).toPromise();
    }

    async consultarUsuario(filtro: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/usuarios/consulta-usuarios`;
        return await this.httpClient.post(url, filtro, {headers}).toPromise();
    }

    async actualizarUsuario(filtro: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/usuarios/actualizacion-usuario`;
        return await this.httpClient.put(url, filtro, {headers}).toPromise();
    }

    async eliminarUsuario(idUsuario: any): Promise<any> {
        const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/usuarios/eliminado-usuario?idUsuario=${idUsuario}`;
        return await this.httpClient.delete(url, {headers}).toPromise();
    }

}