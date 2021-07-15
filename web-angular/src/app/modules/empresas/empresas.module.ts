import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmpresasRoutingModule } from './empresas-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { EmpresaListComponent } from './empresa-list/empresa-list.component';
import { EmpresaFormComponent } from './empresa-form/empresa-form.component';


@NgModule({
  declarations: [EmpresaListComponent, EmpresaFormComponent],
  imports: [
    CommonModule,
    SharedModule,
    EmpresasRoutingModule
  ]
})
export class EmpresasModule { }
