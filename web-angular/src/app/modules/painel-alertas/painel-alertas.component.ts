import { PainelAlertasFiltro } from './../../core/entities/util/painel-alertas-filtro';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-painel-alertas',
  templateUrl: './painel-alertas.component.html',
  styleUrls: ['./painel-alertas.component.scss']
})
export class PainelAlertasComponent implements OnInit {

  //painelAlertasFiltro : PainelAlertasFiltro;

  constructor() { }

  ngOnInit(): void {
    /*this.painelAlertasFiltro = new PainelAlertasFiltro();
    this.painelAlertasFiltro.status = 'NOVO';
    this.painelAlertasFiltro.dias = 30;
    this.painelAlertasFiltro.minutosParaAtualizar = 6;*/
  }

}
