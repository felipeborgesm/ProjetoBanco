package br.com.projetoA3.dto;

import br.com.projetoA3.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateContaResponse {
    private Long id;

    public CreateContaResponse(Conta conta) {
        this.id = conta.getId();
    }
}
