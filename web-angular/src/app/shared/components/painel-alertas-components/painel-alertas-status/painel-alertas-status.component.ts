import { PainelAlertasFiltro } from './../../../../core/entities/util/painel-alertas-filtro';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-painel-alertas-status',
  templateUrl: './painel-alertas-status.component.html',
  styleUrls: ['./painel-alertas-status.component.scss']
})
export class PainelAlertasStatusComponent implements OnInit {

  colunas = ['grupo', 'novo', 'atendido', 'resolvido'];

  constructor() { }

  ngOnInit(): void {
  }

  alterarFiltro(filtro) {
    console.log('Disparou painel-alertas-status!!!');
    console.log(filtro);
  }

}
