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
    private Integer banco;
    private GetTransacaoUsuarioResponse infoUsuario;

    public GetTransacaoContaResponse(Conta conta) {
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.banco = conta.getBanco();
        this.infoUsuario = conta.getUsuario().getUsuarioReduced(conta.getUsuario());
    }
}