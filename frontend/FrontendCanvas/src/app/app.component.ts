import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Aplicación Tareas';
  public isAuthenticated = false;

  public logout(): void {
    // todo
  }
  
}
