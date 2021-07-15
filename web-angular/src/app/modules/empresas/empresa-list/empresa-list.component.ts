import { AuthService } from 'src/app/core/services/auth.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Empresa } from './../../../core/entities/empresa';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AbstractListComponent } from 'src/app/core/abstract-list.component';
import { Title } from '@angular/platform-browser';
import { EmpresaService } from 'src/app/core/services/empresa.service';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-empresa-list',
  templateUrl: './empresa-list.component.html',
  styleUrls: ['./empresa-list.component.scss']
})
export class EmpresaListComponent extends AbstractListComponent<Empresa> implements OnInit {

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private title: Title,
              protected empresaService: EmpresaService,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected matDialog: MatDialog) {
    super(empresaService, mensagemService, authService, matDialog);            
  }

  ngOnInit(): void {
    this.title.setTitle('Lista Empresas');
    this.colunas = ['id', 'nome', 'conta', 'username', 'caixa', 'acoes'];
    this.dataSource.paginator =  this.paginator;
    this.dataSource.sort = this.sort;
  }

}
