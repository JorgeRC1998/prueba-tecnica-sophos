import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class DataService {

    private isAutenthicated = new BehaviorSubject<boolean>(false);
    currentMessage = this.isAutenthicated.asObservable();

    constructor(){

    }

    changeMessage(message: boolean){
        this.isAutenthicated.next(message);
    }

}