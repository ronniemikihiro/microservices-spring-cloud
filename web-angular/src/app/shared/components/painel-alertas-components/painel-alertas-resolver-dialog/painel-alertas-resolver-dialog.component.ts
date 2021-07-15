import { ItemResolver } from '../../../../core/entities/util/item-resolver';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-painel-alertas-resolver-dialog',
  templateUrl: './painel-alertas-resolver-dialog.component.html',
  styleUrls: ['./painel-alertas-resolver-dialog.component.scss']
})
export class PainelAlertasResolverDialogComponent implements OnInit {

  titulo: string;
  formGroup: FormGroup;
  itemResolver: ItemResolver;
  modoEditar = false;

  constructor(private dialogRef: MatDialogRef<PainelAlertasResolverDialogComponent>,
              @Inject(MAT_DIALOG_DATA) protected data: PainelAlertasResolverDialogModel,
              private formBuilder: FormBuilder
  ) { 
    this.titulo = data.titulo;
    this.itemResolver = data.itemResolver;
  }

  ngOnInit(): void {
    this.criarForm();
  }

  private criarForm() {
    this.formGroup = this.formBuilder.group({
      ids: [this.itemResolver.ids, [Validators.required]],
      solucao: [this.itemResolver.solucao, [Validators.required, Validators.maxLength(512)]]
    });
    if (this.itemResolver.status === 'NOVO') {
      this.modoEditar = true;
    } else {
      this.formGroup.controls.solution.disable();
    }
  }

  salvar(): void {
    if (this.formGroup.valid) {
      this.dialogRef.close(this.formGroup.value);
    }
  }

  fecharDialog(): void {
    this.dialogRef.close(null);
  }

  isDesabilitado() {
    return !this.formGroup.valid || !this.modoEditar;
  }

}

export class PainelAlertasResolverDialogModel {
  constructor(public titulo: string, public itemResolver: ItemResolver) {}
}
