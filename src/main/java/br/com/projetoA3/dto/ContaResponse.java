package br.com.projetoA3.dto;

import br.com.projetoA3.model.Conta;
import br.com.projetoA3.model.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class ContaResponse {
    private Long id;
    private Integer numero;
    private Integer agencia;
    private Integer banco;
    private TipoConta tipoConta;
    private BigDecimal saldo;
    private String dataCriacao;

    public ContaResponse(Conta conta) {
        this.numero = conta.getNumero();
        this.agencia = conta.getAgencia();
        this.banco = conta.getBanco();
        this.tipoConta = conta.getTipoConta();
        this.saldo = conta.getSaldo();
        this.dataCriacao = formatarData(conta.getDataCriacao());
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
