import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PainelAlertasFiltroComponent } from './painel-alertas-filtro/painel-alertas-filtro.component';
import { MaterialModule } from 'src/app/material.module';
import { PainelAlertasUltimosAlertasComponent } from './painel-alertas-ultimos-alertas/painel-alertas-ultimos-alertas.component';
import { PainelAlertasResolverDialogComponent } from './painel-alertas-resolver-dialog/painel-alertas-resolver-dialog.component';
import { PainelAlertasMonitorServidoresComponent } from './painel-alertas-monitor-servidores/painel-alertas-monitor-servidores.component';
import { PainelAlertasStatusComponent } from './painel-alertas-status/painel-alertas-status.component';
import { PainelAlertasSinaisComponent } from './painel-alertas-sinais/painel-alertas-sinais.component';


@NgModule({
  declarations: [
    PainelAlertasFiltroComponent,
    PainelAlertasUltimosAlertasComponent,
    PainelAlertasResolverDialogComponent,
    PainelAlertasMonitorServidoresComponent,
    PainelAlertasStatusComponent,
    PainelAlertasSinaisComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MaterialModule
  ],
  exports: [
    PainelAlertasFiltroComponent,
    PainelAlertasUltimosAlertasComponent,
    PainelAlertasMonitorServidoresComponent,
    PainelAlertasStatusComponent,
    PainelAlertasSinaisComponent
  ]
})
export class PainelAlertasComponentsModule { }
