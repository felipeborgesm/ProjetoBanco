package br.com.projetoA3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioRequest {
    private String nome;
    private String cpf;
    private String senha;
}
