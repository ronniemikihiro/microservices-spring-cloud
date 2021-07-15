import { AuthService } from 'src/app/core/services/auth.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Title } from '@angular/platform-browser';
import { AbstractListComponent } from 'src/app/core/abstract-list.component';
import { Perfil } from 'src/app/core/entities/perfil';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { PerfilService } from 'src/app/core/services/perfil.service';

@Component({
  selector: 'app-perfil-list',
  templateUrl: './perfil-list.component.html',
  styleUrls: ['./perfil-list.component.scss']
})
export class PerfilListComponent extends AbstractListComponent<Perfil> implements OnInit {

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private title: Title,
              protected perfilService: PerfilService,
              protected authService: AuthService,
              protected mensagemService: MensagemService,
              protected matDialog: MatDialog) { 
    super(perfilService, mensagemService, authService, matDialog);
  }

  ngOnInit(): void {
    this.title.setTitle('Lista Perfis');
    this.colunas = ['id', 'nome', 'acoes'];
    this.dataSource.paginator =  this.paginator;
    this.dataSource.sort = this.sort;
  }

}
