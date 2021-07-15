import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from 'src/app/core/guards/auth.guard';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';
import { EmpresaFormComponent } from './empresa-form/empresa-form.component';
import { EmpresaListComponent } from './empresa-list/empresa-list.component';

const routes: Routes = [
  {
    path: 'form',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: EmpresaFormComponent }
    ]
  },
  {
    path: 'form/:id',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: EmpresaFormComponent }
    ]
  },
  {
    path: '',
    component: LayoutComponent,
    canActivate: [AuthGuard], 
    canActivateChild: [AuthGuard],
    //data: { roles: ['ADMIN'] },
    children: [
      { path: '', component: EmpresaListComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmpresasRoutingModule { }
