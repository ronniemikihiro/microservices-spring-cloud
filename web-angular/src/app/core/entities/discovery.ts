import { Entidade } from './entidade';

export class Discovery implements Entidade {
    id: number;
    nomeRegra: string;
    data: Date;
    ip: string;
    nome: string;
    mac: string;
    versaoSistema: string;
    operacao: string;
    username: string;
    codigoErro: string;
    codigoRegra: number;
    mensagem: string;
    arquivo: string;
    status: string;
    serialNumber: string;
    hashNumber: string;
    permiteTransacao: number;
    solucao: string;
    ocorrencias: number;
    quantidade = 0;
    novo: boolean;
    criticidade: string;
    nomeGrupo: string;
    sistema: string;
    
    constructor() {
        if (this.id === undefined) { this.id = null; }
        if (this.nomeRegra === undefined) { this.nomeRegra = null; }
        if (this.data === undefined) { this.data = null; }
        if (this.ip === undefined) { this.ip = null; }
        if (this.nome === undefined) { this.nome = null; }
        if (this.mac === undefined) { this.mac = null; }
        if (this.versaoSistema === undefined) { this.versaoSistema = null; }
        if (this.operacao === undefined) { this.operacao = null; }
        if (this.username === undefined) { this.username = null; }
        if (this.codigoErro === undefined) { this.codigoErro = null; }
        if (this.codigoRegra === undefined) { this.codigoRegra = null; }
        if (this.mensagem === undefined) { this.mensagem = null; }
        if (this.arquivo === undefined) { this.arquivo = null; }
        if (this.status === undefined) { this.status = null; }
        if (this.serialNumber === undefined) { this.serialNumber = null; }
        if (this.hashNumber === undefined) { this.hashNumber = null; }
        if (this.permiteTransacao === undefined) { this.permiteTransacao = null; }
        if (this.solucao === undefined) { this.solucao = null; }
        if (this.ocorrencias === undefined) { this.ocorrencias = null; }
        if (this.novo === undefined) { this.novo = null; }
        if (this.criticidade === undefined) { this.criticidade = null; }
        if (this.nomeGrupo === undefined) { this.nomeGrupo = null; }
        if (this.sistema === undefined) { this.sistema = null; }
    }
}
