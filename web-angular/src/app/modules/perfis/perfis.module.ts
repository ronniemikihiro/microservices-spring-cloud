import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PerfisRoutingModule } from './perfis-routing.module';
import { PerfilListComponent } from './perfil-list/perfil-list.component';
import { PerfilFormComponent } from './perfil-form/perfil-form.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [PerfilListComponent, PerfilFormComponent],
  imports: [
    CommonModule,
    SharedModule,
    PerfisRoutingModule
  ]
})
export class PerfisModule { }
