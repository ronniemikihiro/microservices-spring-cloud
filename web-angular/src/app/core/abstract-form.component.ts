import { AuthService } from 'src/app/core/services/auth.service';
import { Entidade } from './entities/entidade';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Util } from './util/util';
import { IGenericoService } from './services/generico.service';
import { MensagemService } from './services/mensagem.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';

export abstract class AbstractFormComponent<T> {

  form: FormGroup;
  idEntidade: number; 

  constructor(protected genericService: IGenericoService<T>,
              protected formBuilder: FormBuilder,
              protected mensagemService: MensagemService,
              protected authService: AuthService,
              protected router: Router,
              protected activatedRoute : ActivatedRoute) { 
    this.obterIdEntidade();                
    this.obterEntidade();
  }

  protected abstract getEntidade(): Entidade;
  protected abstract criarForm(entity: T);
  protected abstract getRotaDestino(): string;

  public salvar() {
    try {
      this.validarPropriedadesForm();
      const entidade = this.formParaEntidade();

      if(!entidade.id) {
        this.genericService.criar(entidade).subscribe(response => {
          this.mensagemService.sucesso('Criado com sucesso!');
          this.router.navigate([this.getRotaDestino()]);
        }, errorResponse => {
          this.mensagemService.erro(errorResponse);
        })
      } else {
        this.genericService.alterar(entidade).subscribe(response => {
          this.mensagemService.sucesso('Alterado com sucesso!');
          this.router.navigate([this.getRotaDestino()]);
        }, errorResponse => {
          this.mensagemService.erro(errorResponse);
        })
      }
    } catch (e) {
      this.mensagemService.notificar(e);
    }
  }

  private obterIdEntidade() {
    const params : Observable<Params> = this.activatedRoute.params;
   
    params.subscribe(urlParams => {
      this.idEntidade = urlParams['id'];
    });
  }

  protected async obterEntidade() {
    try {
      if(this.idEntidade) {
        const entidade = await this.genericService.obterPorId(this.idEntidade).toPromise();
        this.criarForm(entidade);
      } else {
        this.criarForm(undefined);
      }
    } catch (e) {
      this.mensagemService.notificar(e);
    }
  }

  public compararIdsObjetos(o1: any, o2: any): boolean {
    return o1 && o2 ? o1.id === o2.id : false;
  }

  protected validarPropriedadesForm() {
    Util.validarPropriedadesForm(this.form);
  }

  protected formParaEntidade() {
    return Util.formParaEntidade(this.form, this.getEntidade());
  }

  public temRole(roles: string) {
    return this.authService.temRole(roles);
  }

  public temQualquerRole(roles: Array<string>) {
      return this.authService.temQualquerRole(roles);
  }

}