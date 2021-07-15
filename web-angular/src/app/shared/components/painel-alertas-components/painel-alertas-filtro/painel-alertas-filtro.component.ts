import { PainelAlertasFiltro } from './../../../../core/entities/util/painel-alertas-filtro';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit, Input, Output, OnDestroy, EventEmitter } from '@angular/core';
import { Subscription, Observable, timer } from 'rxjs';
import * as moment from 'moment';

@Component({
  selector: 'app-painel-alertas-filtro',
  templateUrl: './painel-alertas-filtro.component.html',
  styleUrls: ['./painel-alertas-filtro.component.scss']
})
export class PainelAlertasFiltroComponent implements OnInit, OnDestroy {
  
  //@Input() painelAlertasFiltro: PainelAlertasFiltro; //Vem setado do PainelAlertasComponent
  @Output() emitterAlteracaoFiltro = new EventEmitter();
  @Input() autoAtualizar = true;

  painelAlertasFiltro: PainelAlertasFiltro;
  formFiltro: FormGroup;
  listStatus = ['NOVO', 'ATENDIDO', 'RESOLVIDO'];
  listDias: number[] = Array.from({length: 120}, (v, k) => k + 1);
  subValoresAlterados : Subscription;

  private subTempo: Subscription;
  private obsCadaSegundo: Observable<number> = timer(0, 1000);
  private dataHoraFinal: moment.Moment;
  private tempoRestante: number;
  private minutosParaAtualizarAnterior: number;
  public minutos: number;
  public segundos: number;

  constructor(private formBuilder: FormBuilder) {
  }
    
  ngOnInit(): void {
    this.iniciarPainelAlertasFiltro();
    this.criarForm();
    this.criarObservacaoAlteracaoFiltro();
    this.iniciarTempo();
  }

  ngOnDestroy(): void {
    if (this.subValoresAlterados) {
      this.subValoresAlterados.unsubscribe();
    }
    if (this.emitterAlteracaoFiltro) {
      this.emitterAlteracaoFiltro.unsubscribe();
    }
    if (this.subTempo) {
      this.subTempo.unsubscribe();
    }
  }

  private iniciarPainelAlertasFiltro() {
    this.painelAlertasFiltro = new PainelAlertasFiltro();
    this.painelAlertasFiltro.status = 'NOVO';
    this.painelAlertasFiltro.dias = 30;
    this.painelAlertasFiltro.minutosParaAtualizar = 6;
  }

  private criarForm() {
    this.formFiltro = this.formBuilder.group({
      status: [this.painelAlertasFiltro.status],
      dias: [this.painelAlertasFiltro.dias, Validators.required],
      minutosParaAtualizar: [this.painelAlertasFiltro.minutosParaAtualizar, Validators.required]
    });
  }

  private criarObservacaoAlteracaoFiltro() {
    this.subValoresAlterados = this.formFiltro.valueChanges.subscribe(value => {
      this.painelAlertasFiltro = value;
      this.emitterAlteracaoFiltro.emit(this.formFiltro.value);
    });
  }

  submit() {
    this.emitterAlteracaoFiltro.emit(this.formFiltro.value);
  }

  private iniciarTempo() {
    if (this.painelAlertasFiltro) {
      this.minutosParaAtualizarAnterior = this.painelAlertasFiltro.minutosParaAtualizar;
      this.dataHoraFinal = moment().add(this.painelAlertasFiltro.minutosParaAtualizar, 'minutes');

      this.subTempo = this.obsCadaSegundo.subscribe((segundos) => {
        if (!this.painelAlertasFiltro.minutosParaAtualizar) {
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

  atualizar() {
    if (this.painelAlertasFiltro) {
      this.dataHoraFinal = moment().add(this.painelAlertasFiltro.minutosParaAtualizar, 'minutes');
    }
    
    console.log('Disparou painel-alertas-filtro!!!');
    console.log(this.formFiltro.value);

    this.emitterAlteracaoFiltro.emit(this.formFiltro.value);
  }

}
