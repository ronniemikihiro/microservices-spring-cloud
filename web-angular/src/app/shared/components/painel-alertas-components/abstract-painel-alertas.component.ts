import { MatTableDataSource } from '@angular/material/table';
import { PainelAlertasFiltro } from '../../../core/entities/util/painel-alertas-filtro';
import { Component, OnInit, Input, Output, EventEmitter, OnDestroy } from '@angular/core';
import { Subscription, Observable, timer } from 'rxjs';
import * as moment from 'moment';

export class GrupoTabela {
  level = 0;
  expandido = false;
  total = 0;
  nome = '';
}

@Component({
  selector: 'app-painel-alertas-components',
  template: `<div>PainelAlertasComponentsComponent</div>`
})
export abstract class AbstractPainelAlertasComponent<T> implements OnInit, OnDestroy {

  @Input() public painelAlertasFiltro: PainelAlertasFiltro;
  @Output() public emitterAlteracaoFiltro = new EventEmitter();
  @Input() public autoAtualizar = true;

  private tempoSubscription: Subscription;
  private dataHoraFinal: moment.Moment;
  private tempoRestante: number;
  private cadaSegundo: Observable<number> = timer(0, 1000);
  private minutosParaAtualizarAnterior: number;
  public minutos: number;
  public segundos: number;

  public colunasExibidas: string[];
  protected agruparPorColunas: string[] = [];
  public items = new MatTableDataSource<T>();
  public isLoadingResults = true;
  
  private evitarAtualizacao = false;

  constructor() { 
  }

  ngOnInit(): void {
    this.init();
    this.iniciarTempo();
    this.atualizar();
  }

  ngOnDestroy(): void {
    if (this.emitterAlteracaoFiltro) {
      this.emitterAlteracaoFiltro.unsubscribe();
    }
    if (this.items) {
      this.items.disconnect();
      this.items.data = null;
    }
    if (this.tempoSubscription) {
      this.tempoSubscription.unsubscribe();
    }
    this.destruir(true);
  }

  public abstract init(): void;
  public abstract destruir(destruir: boolean): void;

  private iniciarTempo() {
    if (this.painelAlertasFiltro) {
      this.minutosParaAtualizarAnterior = this.painelAlertasFiltro.minutosParaAtualizar;
      this.dataHoraFinal = moment().add(this.painelAlertasFiltro.minutosParaAtualizar, 'minutes');

      this.tempoSubscription = this.cadaSegundo.subscribe((segundos) => {
        if(!this.painelAlertasFiltro.minutosParaAtualizar) {
          this.painelAlertasFiltro.minutosParaAtualizar = 1;
        }
        if (this.minutosParaAtualizarAnterior !== this.painelAlertasFiltro.minutosParaAtualizar) {
          this.minutosParaAtualizarAnterior = this.painelAlertasFiltro.minutosParaAtualizar;
          this.dataHoraFinal = moment().add(this.painelAlertasFiltro.minutosParaAtualizar, 'minutes');
        }
        this.tempoRestante = this.dataHoraFinal.diff(moment());
        this.tempoRestante = this.tempoRestante / 1000;
        if (this.tempoRestante <= 0) {
          this.atualizar();
          this.minutos = 0;
          this.segundos = 0;
        } else {
          this.minutos = Math.floor(this.tempoRestante / 60);
          this.segundos = Math.floor(this.tempoRestante - this.minutos * 60);
        }
        if (this.minutos < 0) {
          this.minutos = 0;
        }
        if (this.segundos < 0) {
          this.segundos = 0;
        }
      });
    }
  }

  alterarFiltro(filtro) {
    this.painelAlertasFiltro = filtro;
    this.atualizar();
  }

  atualizar() {
    if (this.painelAlertasFiltro) {
      this.dataHoraFinal = moment().add(this.painelAlertasFiltro.minutosParaAtualizar, 'minutes');
    }
    if (this.autoAtualizar && this.evitarAtualizacao === false) {
      this.destruir(false);
    }
  }

  iniciarAtualizacao() {
    this.evitarAtualizacao = false;
  }

  pararAtualizacao() {
    this.evitarAtualizacao = true;
  }

  pararTempoAtualizacao() {
    this.evitarAtualizacao = true;
    this.autoAtualizar = false;
    if (this.tempoSubscription) {
      this.tempoSubscription.unsubscribe();
    }
  }

  getItems(): MatTableDataSource<T> {
    return this.items;
  }

  getDataItems(): T[] {
    return this.items.data;
  }
  
  applyFilter(filterValue: string) {
    this.items.filter = filterValue.trim().toLowerCase();
    if (this.items.paginator) {
      this.items.paginator.firstPage();
    }
  }

}
