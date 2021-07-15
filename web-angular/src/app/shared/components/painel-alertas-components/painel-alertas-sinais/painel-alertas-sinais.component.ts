import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-painel-alertas-sinais',
  templateUrl: './painel-alertas-sinais.component.html',
  styleUrls: ['./painel-alertas-sinais.component.scss']
})
export class PainelAlertasSinaisComponent implements OnInit, OnDestroy {

  //private dialogRef: MatDialogRef<OkCancelComponent, any>;
  private dialogSub: Subscription;

  colunas = ['row', 'grupo', 'identificador', 'usuario', 'dtAtualizacao', 'ultimaAtualizacao', 'ip', 'execucao', 'acoes'];

  constructor() { }
  
  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    if (this.dialogSub) {
      this.dialogSub.unsubscribe();
    }
    /*if (this.dialogRef) {
      this.dialogRef.close(null);
    }*/
  }

  alterarFiltro(filtro) {
    console.log('Disparou painel-alertas-sinais!!!');
    console.log(filtro);
  }

}
