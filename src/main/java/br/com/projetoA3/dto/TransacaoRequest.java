package br.com.projetoA3.dto;

import java.math.BigDecimal;

import br.com.projetoA3.model.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoRequest {
    private TipoTransacao tipoTransacao;
    private Integer numero;
    private Integer agencia;
    private BigDecimal saldo;
}
