import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';

import { AuthService } from 'src/app/core/services/auth.service';
import { MensagemService } from 'src/app/core/services/mensagem.service';
import { MediaMatcher } from '@angular/cdk/layout';
import { Util } from 'src/app/core/util/util';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    mobileQuery: MediaQueryList
    form: FormGroup;
    loading: boolean;
    mostrarSenha: boolean = false;

    constructor(private formBuilder: FormBuilder,
                private router: Router,
                private mensagemService: MensagemService,
                private authService: AuthService,
                private media: MediaMatcher) {                
        this.mobileQuery = this.media.matchMedia('(max-width: 600px)');
    }

    ngOnInit() {
        this.criarForm();
    }

    private criarForm() {
        const savedUsername = this.authService.getUsername();

        this.form = this.formBuilder.group({
            username: [savedUsername, Validators.required],
            senha: ['', Validators.required],
            lembrarDeMim: [savedUsername !== null]
        });
    }

    login() {
        try {
            this.loading = true;

            Util.validarPropriedadesForm(this.form);

            const username = this.form.get('username').value;
            const senha = this.form.get('senha').value;
            const lembrarDeMim = this.form.get('lembrarDeMim').value;

            this.authService.login(username, senha).subscribe(response => {
                this.authService.salvarToken(response);
                if(lembrarDeMim) {
                    this.authService.salvarUsername(username);
                } else {
                    this.authService.deletarUsername();
                }
                this.mensagemService.sucesso('Logado com sucesso!');
                this.router.navigate(['/']);
            }, errorResponse => {
                this.loading = false;

                if(errorResponse.status === 400) {
                    this.mensagemService.alerta('Usuário ou senha inválido!');
                }
            });
        } catch (e) {
            this.mensagemService.notificar(e);
            this.loading = false;
        }
    }
}
