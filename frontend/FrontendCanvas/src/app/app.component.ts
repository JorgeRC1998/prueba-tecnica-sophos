import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuariosLoginComponent } from './componentes/usuarios-login/usuarios-login.component';
import { DataService } from './servicios/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Aplicación Tareas';
  public isAuthenticated = false;
  authenticated: boolean | undefined;

  constructor(
    private usuariosLoginComponent: UsuariosLoginComponent,
    private _router: Router,
    private dataService: DataService,
  ){
    
  }

  public ngOnInit(): void {
    this.dataService.currentMessage.subscribe(authenticated => this.authenticated = authenticated);
  }

  public ngOnDestroy(): void {
    this.dataService.currentMessage.pipe();
  }

  public login(): void {
    this._router.navigateByUrl('/usuarios-login');
  }

  public logout(): void {
    this.dataService.changeMessage(false);
    sessionStorage.removeItem('access_token');
    this._router.navigateByUrl('/');
  }
  
}
