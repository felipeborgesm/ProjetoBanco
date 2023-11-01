package br.com.projetoA3.dto;

import br.com.projetoA3.model.TipoTransacao;
import br.com.projetoA3.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoResponse {
    private BigDecimal valor;
    private TipoTransacao tipoTransacao;
    private Integer agencia;
    private Integer numero;
    private String dataCriacao;
    private GetTransacaoContaResponse infoConta;

    public TransacaoResponse(Transacao transacao) {
        this.valor = transacao.getValor();
        this.tipoTransacao = transacao.getTipoTransacao();
        this.agencia = transacao.getAgencia();
        this.numero = transacao.getNumero();
        this.dataCriacao = formatarDataCriacao(LocalDateTime.now());
        this.infoConta = transacao.getConta().getContaReduced(transacao.getConta());
    }

    private String formatarDataCriacao(LocalDateTime dataCriacao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dataCriacao.format(formatter);
    }
}