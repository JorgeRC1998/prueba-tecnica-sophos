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
        // const headers = new HttpHeaders({ Authorization: 'Bearer ' +  sessionStorage.getItem('access_token')});
        const url = environment.urlBackendPruebaTecnica + `/auth/login`;
        // return await this.httpClient.post(url, filtro, {headers}).toPromise();
        return await this.httpClient.post(url, filtro).toPromise();
    }

}