import { AuthService } from 'src/app/core/services/auth.service';
import { Entidade } from './entities/entidade';
import { MatTableDataSource } from '@angular/material/table';
import { IGenericoService } from './services/generico.service';
import { MensagemService } from './services/mensagem.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmarDialogComponent, ConfirmarDialogModel } from '../shared/components/confirmar-dialog/confirmar-dialog.component';

export abstract class AbstractListComponent<T> {

    colunas: string[];
    dataSource = new MatTableDataSource<T>();
    valorFiltro: string;

    constructor(protected genericService: IGenericoService<T>,
                protected mensagemService: MensagemService,
                protected authService: AuthService,
                protected matDialog: MatDialog) { }

    public filtrar() {
       this.dataSource.filter = this.valorFiltro.trim().toLowerCase();
    }

    public listarTodos() {
        this.genericService.listarTodos().subscribe(response => {
            this.dataSource.data = response;
    
            if(!this.dataSource.data.length) {
                this.mensagemService.alerta('Nenhum registro encontrado!');
            }
        }, errorResponse => {
            this.mensagemService.erro(errorResponse);
        });
    }

    public confirmarDialogDelete(entidade : Entidade): void {
        const confirmarDialog = this.matDialog.open(ConfirmarDialogComponent, {
            data: new ConfirmarDialogModel("Confirmar", 'Deletar este item?'),
            maxWidth: "400px",
            disableClose: true
        });
    
        confirmarDialog.afterClosed().subscribe(isClicouBotaoSim => {
            if(isClicouBotaoSim) {
                this.genericService.deletar(entidade.id).subscribe(response => {
                    this.mensagemService.sucesso('Deletado com sucesso!');
                    this.listarTodos();
                    this.valorFiltro = "";
                    this.filtrar();
                }, errorResponse => {
                    this.mensagemService.erro(errorResponse);
                });
            }
        });
    }

    public temRole(roles: string) {
        return this.authService.temRole(roles);
    }

    public temQualquerRole(roles: Array<string>) {
        return this.authService.temQualquerRole(roles);
    }
}