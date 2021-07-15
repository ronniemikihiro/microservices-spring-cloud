import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';
import { PermissaoFormComponent } from './permissao-form/permissao-form.component';
import { PermissaoListComponent } from './permissao-list/permissao-list.component';

const routes: Routes = [
  {
    path: 'form',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: PermissaoFormComponent }
    ]
  },
  {
    path: 'form/:id',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: PermissaoFormComponent }
    ]
  },
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard], 
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: PermissaoListComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PermissoesRoutingModule { }
