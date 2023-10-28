package br.com.projetoA3.dto;

import br.com.projetoA3.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTransacaoContaResponse {
    private Integer numero;
    private Integer agencia;

    public GetTransacaoContaResponse(Conta conta) {
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
    }
}
