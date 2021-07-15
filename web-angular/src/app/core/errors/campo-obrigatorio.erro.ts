export class CampoObrigatorioErro extends Error {
    constructor(property: string) {
        super(property + ' é obrigatório!');
        Object.setPrototypeOf(this, CampoObrigatorioErro.prototype);
    }
}