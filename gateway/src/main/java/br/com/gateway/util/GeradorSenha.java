package br.com.gateway.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {

    public static void main(String[] args) {
        final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        System.out.print("Senha admin: ");
        System.out.println(bcrypt.encode("admin"));

        System.out.print("Senha EDIECXA: ");
        System.out.println(bcrypt.encode("EDIECXA"));

        System.out.print("Senha teste: ");
        System.out.println(bcrypt.encode("teste"));
    }

}
