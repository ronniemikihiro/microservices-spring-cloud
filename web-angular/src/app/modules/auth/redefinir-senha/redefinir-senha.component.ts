import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MediaMatcher } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { Util } from 'src/app/core/util/util';

@Component({
  selector: 'app-redefinir-senha',
  templateUrl: './redefinir-senha.component.html',
  styleUrls: ['./redefinir-senha.component.scss']
})
export class RedefinirSenhaComponent implements OnInit {

  mobileQuery: MediaQueryList
  form: FormGroup;
  mostarInfoEnvioEmail: boolean = false;

  constructor(private media: MediaMatcher,
              private mensagemService: MensagemService) {
    this.mobileQuery = this.media.matchMedia('(max-width: 600px)');
  }

  ngOnInit(): void {
    this.criarForm();
  }

  private criarForm() {
    this.form = new FormGroup({
        email: new FormControl('', [Validators.required, Validators.email])
    });
  }

  enviarEmail() {
    try {
      Util.validarPropriedadesForm(this.form);
      this.mostarInfoEnvioEmail = true;
    } catch (e) {
      this.mensagemService.notificar(e);
    }
  }
  
}
