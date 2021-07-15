import { Entidade } from './entidade';
import { GrupoEmpresa } from './grupo-empresa';
import { Usuario } from 'src/app/core/entities/usuario';

export class Empresa implements Entidade {
    id: number;
    nome: string;
    conta: string;
    username: string;
    password: string;
    caixa: string;
    limiteKb: string;
    limiteArquivos: string;
    permiteListar: string;
    usuario: Usuario;
    grupoEmpresa: GrupoEmpresa;
    pastaCompartilhada: string;

    constructor(id?: number, 
                nome?: string, 
                conta?: string,
                username?: string,
                password?: string,
                caixa?: string,
                limiteKb?: string,
                limiteArquivos?: string,
                permiteListar?: string,
                usuario?: Usuario,
                grupoEmpresa?: GrupoEmpresa,
                pastaCompartilhada?: string) {
        this.id = id;
        this.nome = nome;
        this.conta = conta;
        this.username = username;
        this.password = password;
        this.caixa = caixa;
        this.limiteKb = limiteKb;
        this.limiteArquivos = limiteArquivos;
        this.permiteListar = permiteListar;
        this.usuario = usuario;
        this.grupoEmpresa = grupoEmpresa;
        this.pastaCompartilhada = pastaCompartilhada;
    }

}