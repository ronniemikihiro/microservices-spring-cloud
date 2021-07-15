export class ItemResolver {
  ids: number[];
  solucao: string;
  status: string;

  constructor() {
    if (this.ids === undefined) { this.ids = []; }
    if (this.solucao === undefined) { this.solucao = null; }
    if (this.status === undefined) { this.status = null; }
  }
}
