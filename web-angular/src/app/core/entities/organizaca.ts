import { Entidade } from './entidade';
import { Usuario } from 'src/app/core/entities/usuario';

export class Organizacao implements Entidade {
    id: number;
    nome: string;
    cnpj: string;
    codigo: string;
    titulo: string;
    subtitulo: string;
    usuarios: Usuario[];

    constructor(id?: number, 
                nome?: string, 
                cnpj?: string,
                codigo?: string,
                titulo?: string,
                subtitulo?: string,
                usuarios?: Usuario[]) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.codigo = codigo;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.usuarios = usuarios;
    }

}