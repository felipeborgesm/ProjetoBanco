package br.com.projetoA3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUsuarioRequest {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
}
