import { Usuario } from 'src/app/core/entities/usuario';
import { Entidade } from './entidade';

export class UsuarioEmail implements Entidade {
    id: number;
    nome: string;
    usuario: Usuario;

    constructor(id?: number, 
                nome?: string, 
                usuario?: Usuario) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }
}