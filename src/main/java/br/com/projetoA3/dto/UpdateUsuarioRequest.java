package br.com.projetoA3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUsuarioRequest {
    private String nome;
    private String email;
}
