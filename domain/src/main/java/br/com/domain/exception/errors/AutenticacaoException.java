package br.com.domain.exception.errors;

import br.com.domain.exception.rules.RuleException;

public class AutenticacaoException extends RuleException {

    public AutenticacaoException() {
        super("Usuário e/ou senha inválido!");
    }

}
