import { UsuarioGrupo } from './usuario-grupo';
import { Organizacao } from './organizaca';
import { Entidade } from './entidade';
import { Perfil } from 'src/app/core/entities/perfil';
import { UsuarioEmail } from './usuario-email';
import { Empresa } from "./empresa";

export class Usuario implements Entidade {
    id: number;
    nome: string;
    username: string;
    senha: string;
    setor: string;
    cargo: string;
    dominio: string;
    tipoAutenticacao: string;
    empresa: Empresa;
    emails: UsuarioEmail;
    perfil: Perfil;
    tentativaReconexao: number;
    tempoTentativas: number;
    maximoConexoes: number;
    diretorioOrigem: string;
    diretorioDestino: string;
    maximoBanda: number;
    tempoLimpezaLogsBanco: number;
    ultimaLimpezaBanco: Date;
    dnLdap: string;
    quantidadeTentativas: number;
    expiraSenha: Date;
    timeout: number;
    cpf: string;
    certificado: string;
    vertical: string;
    ultimoAcesso: Date;
    telefone: string;
    hashValidation: string;
    ativo: string;
    service: string;
    accessToken: string;
    organizacao: Organizacao;
    multiconexao: string;
    ipClient: string;
    diretorioOrigemCopia: string;
    diretorioDestinoCopia: string;
    mascaraRenomeio: string;
    renomeioCopiaOrigem: string;
    renomeioCopiaDestino: string;
    usuarioGrupos: UsuarioGrupo[];
    compartilharPasta: string;

    constructor(id?: number, 
                nome?: string, 
                username?: string,
                senha?: string,
                setor?: string,
                cargo?: string,
                dominio?: string,
                tipoAutenticacao?: string,
                empresa?: Empresa,
                emails?: UsuarioEmail,
                perfil?: Perfil,
                tentativaReconexao?: number,
                tempoTentativas?: number,
                maximoConexoes?: number,
                diretorioOrigem?: string,
                diretorioDestino?: string,
                maximoBanda?: number,
                tempoLimpezaLogsBanco?: number,
                ultimaLimpezaBanco?: Date,
                dnLdap?: string,
                quantidadeTentativas?: number,
                expiraSenha?: Date,
                timeout?: number,
                cpf?: string,
                certificado?: string,
                vertical?: string,
                ultimoAcesso?: Date,
                telefone?: string,
                hashValidation?: string,
                ativo?: string,
                service?: string,
                accessToken?: string,
                organizacao?: Organizacao,
                multiconexao?: string,
                ipClient?: string,
                diretorioOrigemCopia?: string,
                diretorioDestinoCopia?: string,
                mascaraRenomeio?: string,
                renomeioCopiaOrigem?: string,
                renomeioCopiaDestino?: string,
                usuarioGrupos?: UsuarioGrupo[],
                compartilharPasta?: string) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.setor = setor;
        this.cargo = cargo;
        this.dominio = dominio;
        this.tipoAutenticacao = tipoAutenticacao;
        this.empresa = empresa;
        this.emails = emails;
        this.perfil = perfil;
        this.tentativaReconexao = tentativaReconexao;
        this.tempoTentativas = tempoTentativas;
        this.maximoConexoes = maximoConexoes;
        this.diretorioOrigem = diretorioOrigem;
        this.diretorioDestino = diretorioDestino;
        this.maximoBanda = maximoBanda;
        this.tempoLimpezaLogsBanco = tempoLimpezaLogsBanco;
        this.ultimaLimpezaBanco = ultimaLimpezaBanco;
        this.dnLdap = dnLdap;
        this.quantidadeTentativas = quantidadeTentativas;
        this.expiraSenha = expiraSenha;
        this.timeout = timeout;
        this.cpf = cpf;
        this.certificado = certificado;
        this.vertical = vertical;
        this.ultimoAcesso = ultimoAcesso;
        this.telefone = telefone;
        this.hashValidation = hashValidation;
        this.ativo = ativo;
        this.service = service;
        this.accessToken = accessToken;
        this.organizacao = organizacao;
        this.multiconexao = multiconexao;
        this.ipClient = ipClient;
        this.diretorioOrigemCopia = diretorioOrigemCopia;
        this.diretorioDestinoCopia = diretorioDestinoCopia;
        this.mascaraRenomeio = mascaraRenomeio;
        this.renomeioCopiaOrigem = renomeioCopiaOrigem;
        this.renomeioCopiaDestino = renomeioCopiaDestino;
        this.usuarioGrupos = usuarioGrupos;
        this.compartilharPasta = compartilharPasta;
    }
}