import { AuthService } from 'src/app/core/services/auth.service';
import { Entidade } from '../../../core/entities/entidade';
import { Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { PermissaoService } from "src/app/core/services/permissao.service";
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractFormComponent } from 'src/app/core/abstract-form.component';
import { Permissao } from 'src/app/core/entities/permissao';

@Component({
  selector: 'app-permissao-form',
  templateUrl: './permissao-form.component.html',
  styleUrls: ['./permissao-form.component.scss']
})
export class PermissaoFormComponent extends AbstractFormComponent<Permissao> implements OnInit {

  constructor(private title: Title,
              protected permissaoService: PermissaoService,
              protected formBuilder: FormBuilder,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected router: Router,
              protected activatedRoute : ActivatedRoute) { 
    super(permissaoService, formBuilder, mensagemService, authService, router, activatedRoute);
  }

  ngOnInit(): void {
    this.title.setTitle('Permissões Form');
  }

  protected getEntidade(): Entidade {
    return new Permissao();
  }

  protected getRotaDestino(): string {
    return '/permissoes';
  }

  protected criarForm(permissao: Permissao) {
    this.form = this.formBuilder.group({
      id: [permissao ? permissao.id : ''],
      nome: [permissao ? permissao.nome : '', Validators.required],
      codigo: [permissao ? permissao.codigo : '', Validators.required],
      action: [permissao ? permissao.action : '', Validators.required]
    });
  }

}
