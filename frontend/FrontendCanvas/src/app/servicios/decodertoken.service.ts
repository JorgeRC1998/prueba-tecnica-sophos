import { Injectable } from '@angular/core';
import jwt_decode from 'jwt-decode';

@Injectable({
    providedIn: 'root'
})
export class DecodeService {

    constructor() { }

    decodeToken() {
        let token = null;
        
        if (sessionStorage.getItem('access_token')) {
            token = jwt_decode(sessionStorage.getItem('access_token') || '{}');
        }
        return token;
    }
}
