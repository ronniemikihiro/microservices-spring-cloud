import { ItemResolver } from '../../../../core/entities/util/item-resolver';
import { PainelAlertasResolverDialogComponent } from './../painel-alertas-resolver-dialog/painel-alertas-resolver-dialog.component';
import { MatDialogRef } from '@angular/material/dialog';
import { Discovery } from './../../../../core/entities/discovery';
import { Component, OnInit, QueryList, ViewChildren, OnDestroy } from '@angular/core';
import { AbstractPainelAlertasComponent } from '../abstract-painel-alertas.component';
import { MatCheckbox } from '@angular/material/checkbox';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-painel-alertas-ultimos-alertas',
  templateUrl: './painel-alertas-ultimos-alertas.component.html',
  styleUrls: ['./painel-alertas-ultimos-alertas.component.scss']
})
export class PainelAlertasUltimosAlertasComponent implements OnInit, OnDestroy {
 
  colunas = ['row', 'grupo', 'nomeRegra', 'operacao', 'sistema', 'ocorrencias'];
  agrupar = [];

  private expandedElement: any | null = null;
  @ViewChildren(MatCheckbox) chks: QueryList<MatCheckbox>;

  private dialogRef: MatDialogRef<PainelAlertasResolverDialogComponent, ItemResolver>;
  private dialogSub: Subscription;

  ngOnInit(): void {
    
  }
  ngOnDestroy(): void {
    if (this.dialogSub) {
      this.dialogSub.unsubscribe();
    }
    if (this.dialogRef) {
    this.dialogRef.close(null);
    }
  }
  
  alterarFiltro(filtro) {
    /*this.painelAlertasFiltro = filtro;

    if (this.painelAlertasFiltro.minutosParaAtualizar === filtro.minutosParaAtualizar) {
      this.atualizar();
    }*/
    console.log('Disparou painel-alertas-ultimos-alertas!!!');
    console.log(filtro);
  }

  /*groupHeaderClick(row) {
    if (row.expanded) {
      row.expanded = false;
      this.currentService.closeCurrentGroup();
    } else {
      row.expanded = true;
      this.currentService.openCurrentGroup(row);
    }
  }*/
}
