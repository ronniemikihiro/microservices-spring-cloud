import { AuthService } from 'src/app/core/services/auth.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Title } from '@angular/platform-browser';
import { AbstractListComponent } from 'src/app/core/abstract-list.component';
import { Usuario } from 'src/app/core/entities/usuario';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.scss']
})
export class UsuarioListComponent extends AbstractListComponent<Usuario> implements OnInit {
  
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private title: Title,
              protected usuarioService: UsuarioService,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected matDialog: MatDialog) { 
    super(usuarioService, mensagemService, authService, matDialog);
  }

  ngOnInit(): void {
    this.title.setTitle('Lista Usuários');
    this.colunas = ['id', 'nome', 'username', 'acoes'];
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

}
