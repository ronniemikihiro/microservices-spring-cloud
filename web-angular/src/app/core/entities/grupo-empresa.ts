import { Entidade } from './entidade';
import { Empresa } from "./empresa";

export class GrupoEmpresa implements Entidade {
    id: number;
    nome: string;
    ativo: string;
    empresa: Empresa;    

    constructor(id?: number, 
                nome?: string, 
                ativo?: string,
                empresa?: Empresa) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.empresa = empresa;
    }

}