import { AuthService } from 'src/app/core/services/auth.service';
import { Permissao } from './../../../core/entities/permissao';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { PermissaoService } from "src/app/core/services/permissao.service";
import { AbstractListComponent } from 'src/app/core/abstract-list.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-permissao-list',
  templateUrl: './permissao-list.component.html',
  styleUrls: ['./permissao-list.component.scss']
})
export class PermissaoListComponent extends AbstractListComponent<Permissao> implements OnInit {

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private title: Title,
              protected permissaoService: PermissaoService,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected matDialog: MatDialog) { 
    super(permissaoService, mensagemService, authService, matDialog);
  }

  ngOnInit(): void {
    this.title.setTitle('Lista Permissões');
    this.colunas = ['id', 'nome', 'codigo', 'acoes'];
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

}
