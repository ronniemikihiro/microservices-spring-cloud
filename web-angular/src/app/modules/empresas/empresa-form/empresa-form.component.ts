import { AuthService } from 'src/app/core/services/auth.service';
import { Entidade } from '../../../core/entities/entidade';
import { Empresa } from './../../../core/entities/empresa';
import { Component, OnInit } from '@angular/core';
import { AbstractFormComponent } from 'src/app/core/abstract-form.component';
import { Title } from '@angular/platform-browser';
import { EmpresaService } from 'src/app/core/services/empresa.service';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-empresa-form',
  templateUrl: './empresa-form.component.html',
  styleUrls: ['./empresa-form.component.scss']
})
export class EmpresaFormComponent extends AbstractFormComponent<Empresa> implements OnInit {

  constructor(private title: Title,
              protected empresaService: EmpresaService,
              protected formBuilder: FormBuilder,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected router: Router,
              protected activatedRoute : ActivatedRoute) {
      super(empresaService, formBuilder, mensagemService, authService, router, activatedRoute);
  }

  ngOnInit(): void {
    this.title.setTitle('Empresas Form');
  }

  protected getEntidade(): Entidade {
    return new Empresa();
  }

  protected getRotaDestino(): string {
    return '/empresas';
  }

  protected async criarForm(empresa: Empresa) {
    try {
      this.form = this.formBuilder.group({
        id: [empresa ? empresa.id : ''],
        nome: [empresa ? empresa.nome : '', Validators.required],
        conta: [empresa ? empresa.conta : '', Validators.required],
        username: [empresa ? empresa.username : '', Validators.required],
        password: [empresa ? empresa.password : '', Validators.required],
        caixa: [empresa ? empresa.caixa : '', Validators.required]
      });
    } catch (e) {
      this.mensagemService.notificar(e);
    }   
  }

}
