package br.com.projetoA3.dto;

import br.com.projetoA3.model.TipoTransacao;
import br.com.projetoA3.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoResponse {
    private Integer id;
    private BigDecimal valor;
    private TipoTransacao tipoTransacao;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private GetTransacaoContaResponse conta;

    public TransacaoResponse(Transacao transacao) {
        this.id = transacao.getId();
        this.valor = transacao.getValor();
        this.tipoTransacao = transacao.getTipoTransacao();
        this.agencia = transacao.getAgencia();
        this.dataCriacao = transacao.getDataCriacao();
        this.conta = transacao.getConta().getContaReduced(transacao.getConta());
    }

}
