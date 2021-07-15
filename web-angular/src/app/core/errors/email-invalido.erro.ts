export class EmailInvalidoErro extends Error {
    constructor() {
        super('Email inválido!');
        Object.setPrototypeOf(this, EmailInvalidoErro.prototype);
    }
}