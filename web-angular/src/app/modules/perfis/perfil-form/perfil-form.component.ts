import { AuthService } from 'src/app/core/services/auth.service';
import { Entidade } from '../../../core/entities/entidade';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractFormComponent } from 'src/app/core/abstract-form.component';
import { Perfil } from 'src/app/core/entities/perfil';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { PerfilService } from 'src/app/core/services/perfil.service';
import { Validators, FormBuilder } from '@angular/forms';
import { Permissao } from 'src/app/core/entities/permissao';
import { PermissaoService } from 'src/app/core/services/permissao.service';

@Component({
  selector: 'app-perfil-form',
  templateUrl: './perfil-form.component.html',
  styleUrls: ['./perfil-form.component.scss']
})
export class PerfilFormComponent extends AbstractFormComponent<Perfil> implements OnInit {

  permissoes: Permissao[];
  permissoesFiltered: Permissao[];

  constructor(private title: Title,
              protected formBuilder: FormBuilder,
              protected perfilService: PerfilService,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected router: Router,
              protected activatedRoute : ActivatedRoute,
              private permissaoService: PermissaoService) { 
    super(perfilService, formBuilder, mensagemService, authService, router, activatedRoute);
  }

  ngOnInit(): void {
    this.title.setTitle('Perfis Form');
    this.listarPermissoes();
  }

  protected getEntidade(): Entidade {
    return new Perfil();
  }

  protected getRotaDestino(): string {
    return '/perfis';
  }

  protected async criarForm(perfil: Perfil) {
    try {
      if(perfil && perfil.id) {
        perfil.permissoes = await this.listarPermissoesByPerfil(perfil);
      }

      this.form = this.formBuilder.group({
        id: [perfil ? perfil.id : ''],
        nome: [perfil ? perfil.nome : '', Validators.required],
        permissoes: [perfil ? perfil.permissoes : '']
      });
    } catch (e) {
      this.mensagemService.notificar(e);
    }   
  }

  private listarPermissoes() {
    this.permissaoService.listarTodos().subscribe(response => {
      this.permissoes = response.sort((p1, p2) => (p1.nome < p2.nome ? -1 : 1));
      this.permissoesFiltered = this.permissoes.slice();
    }, errorResponse => {
      this.mensagemService.erro(errorResponse);
    });
  }

  private listarPermissoesByPerfil(perfil: Perfil): Promise<Permissao[]> {
    return this.permissaoService.obterPorPerfil(perfil.id).toPromise();
  }

}
