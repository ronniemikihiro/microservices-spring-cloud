import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PainelAlertasRoutingModule } from './painel-alertas-routing.module';
import { PainelAlertasComponent } from './painel-alertas.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    PainelAlertasComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    PainelAlertasRoutingModule
  ]
})
export class PainelAlertasModule { }
