import { GrupoUsuarios } from './grupo-usuarios';
import { Usuario } from 'src/app/core/entities/usuario';
import { Entidade } from './entidade';

export class UsuarioGrupo implements Entidade {
    id: number;
    dataCriacao: Date;
    dataInicio: Date;
    dataFim: Date;
    ativo: boolean;
    usuario: Usuario;
    grupoUsuarios: GrupoUsuarios;

    constructor(id?: number, 
                dataCriacao?: Date, 
                dataInicio?: Date, 
                dataFim?: Date,
                ativo?: boolean,
                usuario?: Usuario,
                grupoUsuarios?: GrupoUsuarios) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = ativo;
        this.usuario = usuario;
        this.grupoUsuarios = grupoUsuarios;
    }
}