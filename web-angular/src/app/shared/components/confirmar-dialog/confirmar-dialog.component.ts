import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirmar-dialog',
  templateUrl: './confirmar-dialog.component.html',
  styleUrls: ['./confirmar-dialog.component.scss']
})
export class ConfirmarDialogComponent implements OnInit {
  titulo: string;
  mensagem: string;

  constructor(private dialogRef: MatDialogRef<ConfirmarDialogComponent>,
              @Inject(MAT_DIALOG_DATA) private data: ConfirmarDialogModel) {
    this.titulo = data.titulo;
    this.mensagem = data.mensagem;
  }

  ngOnInit() {
  }

  confirmar(): void {
    this.dialogRef.close(true);
  }

  fecharDialog(): void {
    this.dialogRef.close(false);
  }
}

export class ConfirmarDialogModel {
  constructor(public titulo: string, public mensagem: string) {}
}
