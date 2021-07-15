import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';

import { LoginComponent } from './login/login.component';
import { RedefinirSenhaComponent } from './redefinir-senha/redefinir-senha.component';
import { NaoAutorizadoComponent } from './nao-autorizado/nao-autorizado.component';

const routes: Routes = [
  { 
    path: 'login', 
    component: LoginComponent 
  },
  { 
    path: 'redefinir-senha', 
    component: RedefinirSenhaComponent 
  },
  {
    path: 'nao-autorizado',
    component: LayoutComponent,
    children: [
      { path: '', component: NaoAutorizadoComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
