import { Entidade } from './entidade';
import { Permissao } from './permissao';
import { Usuario } from './usuario';

export class Perfil implements Entidade {
    id: number;
    nome: string;
    usuarios: Usuario[];
    permissoes: Permissao[];

    constructor(id?: number, 
                nome?: string, 
                usuarios?: Usuario[], 
                permissoes?: Permissao[]) {
        this.id = id;
        this.nome = nome;
        this.usuarios = usuarios;
        this.permissoes = permissoes;
    }
}