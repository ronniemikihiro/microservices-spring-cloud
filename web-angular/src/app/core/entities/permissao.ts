import { Entidade } from './entidade';

export class Permissao implements Entidade {
    id: number;
    nome: string;
    codigo: string;
    action: string;

    constructor(id?: number, 
                nome?: string, 
                codigo?: string, 
                action?: string) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.action = action;
    }
}