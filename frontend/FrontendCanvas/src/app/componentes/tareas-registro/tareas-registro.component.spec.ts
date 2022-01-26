import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TareasRegistroComponent } from './tareas-registro.component';

describe('TareasRegistroComponent', () => {
  let component: TareasRegistroComponent;
  let fixture: ComponentFixture<TareasRegistroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TareasRegistroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TareasRegistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
