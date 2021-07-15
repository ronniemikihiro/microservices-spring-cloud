import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
import { UsuarioListComponent } from './usuario-list/usuario-list.component';

const routes: Routes = [
  {
    path: 'form',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: UsuarioFormComponent }
    ]
  },
  {
    path: 'form/:id',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: UsuarioFormComponent }
    ]
  },
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard], 
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: UsuarioListComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuariosRoutingModule { }
