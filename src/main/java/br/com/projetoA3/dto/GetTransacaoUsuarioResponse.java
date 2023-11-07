package br.com.projetoA3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTransacaoUsuarioResponse {
    private String nome;
    private String cpf;
    private String email;
    
}
