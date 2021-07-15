import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';
import { PerfilFormComponent } from './perfil-form/perfil-form.component';
import { PerfilListComponent } from './perfil-list/perfil-list.component';

const routes: Routes = [
  {
    path: 'form',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: PerfilFormComponent }
    ]
  },
  {
    path: 'form/:id',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: PerfilFormComponent }
    ]
  },
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard], 
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: PerfilListComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PerfisRoutingModule { }
