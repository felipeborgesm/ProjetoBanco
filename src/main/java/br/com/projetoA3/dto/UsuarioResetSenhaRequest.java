package br.com.projetoA3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResetSenhaRequest {
    private String token;
    private String senha;

}
