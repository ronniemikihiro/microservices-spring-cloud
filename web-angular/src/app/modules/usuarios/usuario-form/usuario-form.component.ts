import { AuthService } from 'src/app/core/services/auth.service';
import { TipoAutenticacaoService } from '../../../core/services/tipo-autenticacao.service';
import { Entidade } from '../../../core/entities/entidade';
import { Title } from '@angular/platform-browser';
import { Usuario } from 'src/app/core/entities/usuario';
import { Component, OnInit } from '@angular/core';
import { AbstractFormComponent } from 'src/app/core/abstract-form.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { UsuarioService } from 'src/app/core/services/usuario.service';
import { Validators, FormBuilder } from '@angular/forms';
import { EmpresaService } from 'src/app/core/services/empresa.service';
import { PerfilService } from 'src/app/core/services/perfil.service';
import { UsuarioFormBean } from './usuario-form-bean';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent extends AbstractFormComponent<Usuario> implements OnInit {

  bean: UsuarioFormBean = new UsuarioFormBean();

  constructor(private title: Title,
              protected usuarioService: UsuarioService,
              protected formBuilder: FormBuilder,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected router: Router,
              protected activatedRoute : ActivatedRoute,
              private tipoAutenticacaoService: TipoAutenticacaoService,
              private empresaService: EmpresaService,
              private perfilService: PerfilService) { 
      super(usuarioService, formBuilder, mensagemService, authService, router, activatedRoute);
    }

  ngOnInit(): void {
    this.title.setTitle('Usuários Form');
    this.listarTiposAutenticacao();
    this.listarEmpresas();
    this.listarPerfis();
  }

  protected getEntidade(): Entidade {
    return new Usuario();
  }

  protected getRotaDestino(): string {
    return '/usuarios';
  }

  protected criarForm(usuario: Usuario) {
    try {
      this.form = this.formBuilder.group({
        id: [usuario ? usuario.id : ''],
        nome: [usuario ? usuario.nome : '', Validators.required],
        username: [usuario ? usuario.username : '', Validators.required],
        senha: [usuario ? usuario.senha : '', Validators.required],
        setor: [usuario ? usuario.setor : ''],
        cargo: [usuario ? usuario.cargo : '', Validators.required],
        dominio: [usuario ? usuario.dominio : ''],
        tipoAutenticacao: [usuario ? usuario.tipoAutenticacao : '', Validators.required],
        empresa: [usuario ? usuario.empresa : '', Validators.required],
        perfil: [usuario ? usuario.perfil : '', Validators.required]
      });
    } catch (e) {
      this.mensagemService.notificar(e);
    }   
  }

  private listarTiposAutenticacao() {
    this.tipoAutenticacaoService.listarTodos().subscribe(response => {
      this.bean.tipoAutenticacoes = response;
      this.bean.tipoAutenticacoesFiltered = this.bean.tipoAutenticacoes.slice();
    }, errorResponse => {
      this.mensagemService.erro(errorResponse);
    });
  }

  private listarEmpresas() {
    this.empresaService.listarTodos().subscribe(response => {
      this.bean.empresas = response;
      this.bean.empresasFiltered = this.bean.empresas.slice();
    }, errorResponse => {
      this.mensagemService.erro(errorResponse);
    });
  }

  private listarPerfis() {
    this.perfilService.listarTodos().subscribe(response => {
      this.bean.perfis = response;
      this.bean.perfisFiltered = this.bean.perfis.slice();
    }, errorResponse => {
      this.mensagemService.erro(errorResponse);
    });
  }

}
