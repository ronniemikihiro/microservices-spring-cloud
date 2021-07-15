import { HttpErrorResponse } from '@angular/common/http';
import { EmailInvalidoErro } from './../errors/email-invalido.erro';
import { CampoObrigatorioErro } from './../errors/campo-obrigatorio.erro';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
    providedIn: 'root'
})
export class MensagemService {

    constructor(private snackBar: MatSnackBar) { }

    private mostrar(message: string, background: string) {
        this.snackBar.open(message, '', {
            duration: 2000,
            horizontalPosition: 'end',
            panelClass: ['mat-toolbar', background]
        });
    }

    public sucesso(message: string) {
        this.mostrar(message, 'mat-primary');
    }

    public alerta(message: string) {
        this.mostrar(message, 'mat-accent');
    }

    public erro(erro: any) {
        if(erro instanceof HttpErrorResponse) {
            const message = erro.error.message ? erro.error.message : ''; 
            this.mostrar(erro.error.error + "! " + (message === 'GENERAL' ? '' : message), 'mat-warn');
            console.error(erro);
        } else {
            this.mostrar(erro, 'mat-warn');
        }
    }

    public notificar(e : Error) {
        if(e instanceof CampoObrigatorioErro || 
           e instanceof EmailInvalidoErro) {
            this.alerta(e.message);
        } else {
            this.erro(e.message);
        }
    }
}
