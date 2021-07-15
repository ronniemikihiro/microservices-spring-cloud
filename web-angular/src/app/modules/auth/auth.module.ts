import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { LoginComponent } from './login/login.component';
import { NaoAutorizadoComponent } from './nao-autorizado/nao-autorizado.component';
import { RedefinirSenhaComponent } from './redefinir-senha/redefinir-senha.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AuthRoutingModule
  ],
  declarations: [
    LoginComponent, 
    NaoAutorizadoComponent, 
    RedefinirSenhaComponent
  ]
})
export class AuthModule { }
