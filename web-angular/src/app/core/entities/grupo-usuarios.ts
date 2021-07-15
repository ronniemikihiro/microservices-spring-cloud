import { GrupoUsuariosMetadados } from './grupo-usuarios-metadados';
import { Entidade } from './entidade';
import { UsuarioGrupo } from './usuario-grupo';

export class GrupoUsuarios implements Entidade {
    id: number;
    nome: string;
    ativo: boolean;
    grupoPai: GrupoUsuarios;
    usuarioGrupos: UsuarioGrupo[];
    metadados: GrupoUsuariosMetadados;

    constructor(id?: number, 
                nome?: string, 
                ativo?: boolean, 
                grupoPai?: GrupoUsuarios,
                usuarioGrupos?: UsuarioGrupo[],
                metadados?: GrupoUsuariosMetadados) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.grupoPai = grupoPai;
        this.usuarioGrupos = usuarioGrupos;
        this.metadados = metadados;
    }
}