package br.com.projetoA3.model;

import java.util.UUID;

public class TokenUtils {

    public static String gerarToken() {
        return UUID.randomUUID().toString();
    }
}