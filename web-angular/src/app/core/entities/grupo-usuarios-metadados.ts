import { Entidade } from './entidade';
import { GrupoUsuarios } from './grupo-usuarios';

export class GrupoUsuariosMetadados implements Entidade {
    id: number;
    key: string;
    value: string;
    grupoUsuarios: GrupoUsuarios;

    constructor(id?: number, 
                key?: string, 
                value?: string, 
                grupoUsuarios?: GrupoUsuarios) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.grupoUsuarios = grupoUsuarios;
    }
}