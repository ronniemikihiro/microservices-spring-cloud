import { PainelAlertasComponentsModule } from './components/painel-alertas-components/painel-alertas-components.module';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LimitToPipe } from './pipes/limit-to.pipe';
import { LocalDatePipe } from './pipes/local-date.pipe';
import { YesNoPipe } from './pipes/yes-no.pipe';
import { LayoutComponent } from './layout/layout.component';
import { MaterialModule } from '../material.module';
import { ConfirmarDialogComponent } from './components/confirmar-dialog/confirmar-dialog.component';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { getPortuguesePaginatorIntl } from './ptbr-components/mat-paginator-ptbr';

@NgModule({
  declarations: [
    LayoutComponent,
    LimitToPipe,
    LocalDatePipe,
    YesNoPipe,
    ConfirmarDialogComponent,
  ],
  imports: [
    RouterModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    PainelAlertasComponentsModule
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MaterialModule,
    LimitToPipe,
    LocalDatePipe,
    YesNoPipe,
    ConfirmarDialogComponent,
    PainelAlertasComponentsModule
  ],
  entryComponents: [
    ConfirmarDialogComponent
  ],
  providers: [
    { provide: MatPaginatorIntl, useValue: getPortuguesePaginatorIntl() }
  ]
})
export class SharedModule { }
