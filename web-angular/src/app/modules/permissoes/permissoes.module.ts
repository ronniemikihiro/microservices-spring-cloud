import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PermissoesRoutingModule } from './permissoes-routing.module';
import { PermissaoListComponent } from './permissao-list/permissao-list.component';
import { PermissaoFormComponent } from './permissao-form/permissao-form.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [PermissaoListComponent, PermissaoFormComponent],
  imports: [
    CommonModule,
    SharedModule,
    PermissoesRoutingModule
  ]
})
export class PermissoesModule { }
